package com.weaveown.limit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author wangwei
 * @date 2021/4/1
 */
public class TimeWindowLimit {
    public static LoadingCache<Long, AtomicLong> counter =
            CacheBuilder.newBuilder().expireAfterWrite(2, TimeUnit.SECONDS).build(new CacheLoader<Long, AtomicLong>() {
                @Override
                public AtomicLong load(Long seconds) throws Exception {
                    return new AtomicLong(0);
                }
            });

    public static void main(String[] args) {
        long limit = 100;
        while (true) {
            long seconds = System.currentTimeMillis() / 1000;
            try {
                if (counter.get(seconds).incrementAndGet() > limit) {
                    System.out.println(2);
//                    break;
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println(1);
        }

    }
}
