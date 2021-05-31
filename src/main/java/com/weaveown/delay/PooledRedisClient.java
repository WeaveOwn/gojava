package com.weaveown.delay;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author wangwei
 * @date 2021/5/25
 */
@Slf4j
public class PooledRedisClient {
    private static final int DEFAULT_RETRY_TIME = 3;

    private static final int MIN_RETRY_TIME = 0;

    private static final int MAX_RETRY_TIME = 5;

    private static final long REDIS_EXECUTE_WARN_TIME = 50;

    private final JedisPool pool;

    public PooledRedisClient(JedisPool pool) {
        this.pool = pool;
    }

    public void run(Consumer<Jedis> command) {

        execute(jedis -> {
            command.accept(jedis);
            return null;
        });
    }

    public void runWithRetry(Consumer<Jedis> command) {
        runWithRetry(command, DEFAULT_RETRY_TIME);
    }

    public void runWithRetry(Consumer<Jedis> command, int retries) {
        executeWithRetry(jedis -> {
            command.accept(jedis);
            return null;
        }, retries);
    }

    public <T> T execute(Function<Jedis, T> command) {
        long startTime = System.currentTimeMillis();
        Jedis jedis = null;
        try {
            jedis = this.pool.getResource();
            return command.apply(jedis);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
            long endTime = System.currentTimeMillis();
            long useTime = endTime - startTime;
            if (useTime > REDIS_EXECUTE_WARN_TIME) {
                log.warn("pooled redis client execute time warn. useTime:[{}],command:[{}]", useTime, command);
            }
        }
    }

    public <T> T executeWithRetry(Function<Jedis, T> command) {
        return executeWithRetry(command, DEFAULT_RETRY_TIME);
    }

    public <T> T executeWithRetry(Function<Jedis, T> command, int retryTime) {
        if (retryTime <= MIN_RETRY_TIME || retryTime > MAX_RETRY_TIME) {
            retryTime = DEFAULT_RETRY_TIME;
        }
        int failTime = 0;

        T result;
        while (true) {
            long startTime = System.currentTimeMillis();
            try (Jedis jedis = this.pool.getResource()) {
                log.debug("pooled redis client execute start.command:[{}]", command);
                result = command.apply(jedis);
                break;
            } catch (RuntimeException e) {
                failTime++;
                if (failTime >= retryTime) {
                    throw e;
                }
            } finally {
                long useTime = System.currentTimeMillis() - startTime;
                if (useTime > REDIS_EXECUTE_WARN_TIME) {
                    log.warn("pooled redis client execute time warn. useTime:[{}],command:[{}]", useTime, command);
                }
            }
        }
        return result;
    }

    public JedisPool getPool() {
        return this.pool;
    }
}
