//package com.weaveown.cache;
//
//import lombok.Setter;
//import org.springframework.data.redis.core.RedisTemplate;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * @author wangwei
// * @date 2021/5/31
// */
//
//public class XCacheFactory {
//    private boolean useRedis;
//
//    @Setter
//    private RedisTemplate redisTemplate;
//
//    public XCacheFactory(boolean userRedis) {
//        this.useRedis = userRedis;
//    }
//
//    public <K, V> XCache<K, V> newInstance(int maxSize, long expire, TimeUnit timeUnit) {
//        if (useRedis && redisTemplate != null) {
//            return new RedisXCache<>(redisTemplate, expire, timeUnit);
//        }
//        return new LocalXCache<>(maxSize, expire, timeUnit);
//    }
//}
