package com.weaveown.algorithm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wangwei
 * @date 2020/8/26
 */
public class LRU<K, V> implements Iterable {
    private Map<K, V> lruCache;
    private int MAX_CAPACITY;

    @Override
    public Iterator iterator() {
        return this.lruCache.entrySet().iterator();
    }

    public LRU(int capacity) {
        MAX_CAPACITY = (int) Math.ceil(capacity / 0.75f) + 1;
        lruCache = new LinkedHashMap<K, V>(MAX_CAPACITY, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }


        };

    }

    public synchronized void put(K key, V value) {
        this.lruCache.put(key, value);
    }

    public synchronized V get(K key) {
        return lruCache.get(key);
    }

    public static void main(String[] args) {
//        LRU<String, String> lru = new LRU<>(5);
//        for (int i = 0; i < 10; i++) {
//            lru.put(i + "", i + "");
//        }
//        Iterator iterator = lru.iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<String, String> next = (Map.Entry<String, String>) iterator.next();
//            System.out.println(next.getKey() + "-" + next.getValue());
//        }
        HashMap<String, String> hashMap = new HashMap<>(2);
        hashMap.put("1", "1");
        hashMap.put("2", "2");
        hashMap.put("3", "3");

    }

}
