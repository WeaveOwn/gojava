package com.weaveown.cache;

import com.google.common.cache.Cache;

import java.util.concurrent.TimeUnit;

import static com.google.common.cache.CacheBuilder.newBuilder;

/**
 * @author wangwei
 * @date 2021/5/31
 */
public class LocalXCache<K, V> implements XCache<K, V> {
    private final Cache<K, V> cache;

    public LocalXCache(int maxSize, long expire, TimeUnit timeUnit) {
        cache = newBuilder().maximumSize(maxSize).expireAfterAccess(expire, timeUnit).build();
    }

    @Override
    public synchronized boolean setNx(K key, V value) {
        if (contains(key)) {
            return false;
        }

        set(key, value);
        return true;
    }

    @Override
    public void set(K key, V value) {
        cache.put(key, value);
    }

    @Override
    public V get(K key) {
        return cache.asMap().get(key);
    }

    @Override
    public boolean contains(K key) {
        return cache.asMap().containsKey(key);
    }

    @Override
    public void remove(K key) {
        cache.invalidate(key);
    }
}
