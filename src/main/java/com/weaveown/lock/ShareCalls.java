package com.weaveown.lock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

/**
 * @author wangwei
 * @date 2021/3/31
 */
public class ShareCalls<T> {


    private static class Call<T> {
        private CountDownLatch countDownLatch;
        private T data;
        private boolean hasCache;

        public boolean getHasCache() {
            return hasCache;
        }

        public void setHasCache(boolean hasCache) {
            this.hasCache = hasCache;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public CountDownLatch getCountDownLatch() {
            return countDownLatch;
        }

        public Call() {
            countDownLatch = new CountDownLatch(1);
        }
    }

    public ShareCalls() {
        calls = new ConcurrentHashMap<>();
        lock = new ReentrantLock();
    }

    private Map<String, Call> calls;
    private Lock lock;

    public T call(String key, Supplier<T> supplier) {
        Call<T> c = createCall(key);
        if (c.getHasCache()) {
            return c.getData();
        }
        makeCall(c, key, supplier);
        return c.getData();

    }


    private Call<T> createCall(String key) {
        lock.lock();
        if (calls.containsKey(key)) {
            lock.unlock();
            Call c = calls.get(key);
            c.setHasCache(true);
            try {
                c.countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return c;
        }

        Call<T> c = new Call<>();
        c.setHasCache(false);
        calls.put(key, c);
        lock.unlock();
        return c;
    }

    private void makeCall(Call<T> c, String key, Supplier<T> supplier) {
        c.setData(supplier.get());
        lock.lock();
        calls.remove(key);
        lock.unlock();
        c.getCountDownLatch().countDown();
    }
}
