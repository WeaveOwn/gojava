package com.weaveown.delay;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.Setter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author wangwei
 * @date 2021/5/25
 */
public class DelayedDemo {


    public static void main(String[] args) throws InterruptedException {
        final DelayedFactory delayedFactory = new DelayedFactory(false);
        final IDelayedQueue<JobDelayed> delayedQueue = delayedFactory.newInstance("WEAVEOWN");
        JobDelayed<String> jobDelayed = new JobDelayed<>("job1", "job1", 5, TimeUnit.SECONDS);
        JobDelayed<String> jobDelayed2 = new JobDelayed<>("job2", "job1", 6, TimeUnit.SECONDS);
        JobDelayed<String> jobDelayed3 = new JobDelayed<>("job3", "job3", 7, TimeUnit.SECONDS);
        JobDelayed<String> jobDelayed4 = new JobDelayed<>("job4", "job4", 8, TimeUnit.SECONDS);
        JobDelayed<String> jobDelayed5 = new JobDelayed<>("job5", "job4", 8, TimeUnit.SECONDS);
        delayedQueue.put(jobDelayed);
        delayedQueue.put(jobDelayed2);
        delayedQueue.put(jobDelayed3);
        delayedQueue.put(jobDelayed4);

        for (; ; ) {
            final JobDelayed take = delayedQueue.take();
            if (take == null) {
                Thread.sleep(200);
                continue;
            }
            System.out.println(take.getItem());
        }
    }

    @Data
    static class JobDelayed<T> implements Delayed {
        private String key;
        private T item;
        private long expireTime;

        public JobDelayed() {

        }

        public JobDelayed(String key, T item, long time, TimeUnit timeUnit) {
            this.key = key;
            this.item = item;
            this.expireTime = System.currentTimeMillis() + (time > 0 ? timeUnit.toMillis(time) : 0);
        }

        @Override
        public long getDelay(TimeUnit unit) {
            // 被DelayQueue循环调用
            return expireTime - System.currentTimeMillis();
        }

        @Override
        public int compareTo(Delayed o) {
            JobDelayed item = (JobDelayed) o;

            long diff = this.expireTime - item.expireTime;
            if (diff <= 0) {// 改成>=会造成问题
                return -1;
            } else {
                return 1;
            }
        }

        @Override
        public boolean equals(Object o) {
            return this.key.equals(((JobDelayed) o).getKey());
        }
    }

    static class DelayedFactory {
        private boolean useRedis;

        public DelayedFactory(boolean userRedis) {
            this.useRedis = userRedis;
        }

        public IDelayedQueue newInstance(String namespace) {
            if (useRedis) {
                final RedisDelayedQueue delayedQueue = new RedisDelayedQueue(namespace);
                delayedQueue.setJedisPool(createJedisPool());
                return delayedQueue;
            }
            final LocalDelayedQueue delayedQueue = new LocalDelayedQueue();
            delayedQueue.setDelayQueue(new DelayQueue<>());
            return delayedQueue;
        }

        private JedisPool createJedisPool() {
            JedisPool jedisPool = new JedisPool();
            return jedisPool;
        }
    }

    interface IDelayedQueue<E> {
        void put(E item);

        boolean contains(E item);


        E take() throws InterruptedException;
    }

    static class LocalDelayedQueue implements IDelayedQueue<JobDelayed> {

        @Setter
        private DelayQueue<JobDelayed> delayQueue;

        @Override
        public void put(JobDelayed item) {
            delayQueue.put(item);
        }

        @Override
        public JobDelayed take() throws InterruptedException {
            return delayQueue.take();
        }

        @Override
        public boolean contains(JobDelayed item) {
            return delayQueue.contains(item);
        }

    }

    static class RedisDelayedQueue implements IDelayedQueue<JobDelayed> {
        private static final String POP_SCRIPT = "local rlt = redis.call('ZRANGEBYSCORE', KEYS[1], '-inf', ARGV[1], 'LIMIT', '0', ARGV[2])"
                + " if next(rlt) ~= nil then redis.call('ZREM', KEYS[1], unpack(rlt)) end"
                + " return rlt";
        private String key;


        public RedisDelayedQueue(String key) {
            this.key = key;
        }

        @Setter
        private JedisPool jedisPool;

        @Override
        public void put(JobDelayed item) {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.zadd(key, item.getDelay(TimeUnit.MILLISECONDS), JSON.toJSONString(item));
            }
        }

        @Override
        public boolean contains(JobDelayed item) {
            try (Jedis jedis = jedisPool.getResource()) {
                final Double zscore = jedis.zscore(key, JSON.toJSONString(item));
                if (zscore == null) {
                    return false;
                }

                return zscore > 0.0;
            }
        }


        @Override
        public JobDelayed take() throws InterruptedException {
            try (Jedis jedis = jedisPool.getResource()) {
                final List<String> items = (List) jedis.eval(POP_SCRIPT, 1, key, System.currentTimeMillis() + "", 1 + "");
                if (items != null && !items.isEmpty()) {
                    return JSON.parseObject(items.get(0), JobDelayed.class);
                }
            }
            return null;
        }
    }
}
