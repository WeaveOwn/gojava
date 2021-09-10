//package com.weaveown.cache;
//
//import com.google.common.base.Objects;
//import com.google.common.collect.Lists;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.script.DefaultRedisScript;
//import org.springframework.data.redis.core.script.RedisScript;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * @author wangwei
// * @date 2021/5/31
// */
//public class RedisXCache<K, V> implements XCache<K, V> {
//    private static String setExNxScript = "return redis.call('set', KEYS[1],ARGV[1],'EX',ARGV[2],'NX')";
//
//    private final RedisTemplate redisTemplate;
//    private final long expire;
//    private final TimeUnit timeUnit;
//
//    public RedisXCache(RedisTemplate redisTemplate, long expire, TimeUnit timeUnit) {
//        this.redisTemplate = redisTemplate;
//        this.expire = expire;
//        this.timeUnit = timeUnit;
//    }
//
//    @Override
//    public void set(K key, V value) {
//        redisTemplate.opsForValue().set(key, value, expire, timeUnit);
//    }
//
//    @Override
//    public boolean setNx(K key, V value) {
//        RedisScript<String> redisScript = new DefaultRedisScript(setExNxScript, String.class);
//        String result = (String) this.redisTemplate.execute(redisScript, Lists.newArrayList(key), value, timeUnit.toSeconds(expire) + "");
//        if (Objects.equal("OK", result)) {
//            return Boolean.TRUE;
//        }
//        return Boolean.FALSE;
//
//    }
//
//    @Override
//    public V get(K key) {
//        return (V) redisTemplate.opsForValue().get(String.valueOf(key));
//
//    }
//
//    @Override
//    public boolean contains(K key) {
//        return redisTemplate.hasKey(key);
//    }
//
//    @Override
//    public void remove(K key) {
//        redisTemplate.delete(key);
//    }
//}
