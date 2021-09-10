package com.weaveown.cache;

/**
 * @author wangwei
 * @date 2021/5/31
 */
public interface XCache<K, V> {

    void set(K key, V value);

    boolean setNx(K key, V value);

    V get(K key);

    boolean contains(K key);

    void remove(K key);
}
