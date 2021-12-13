package com.weaveown.lock;

import java.util.HashMap;
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
public class ShareCalls {


    private final Map<String, Call> calls;
    private final Lock lock;

    public ShareCalls() {
        calls = new ConcurrentHashMap<>();
        lock = new ReentrantLock();
    }

    public <T> T call(String key, Supplier<T> supplier) {
        Call<T> c = createCall(key);
        if (c.getHasCache()) {
            return c.getData();
        }
        makeCall(c, key, supplier);
        return c.getData();

    }


    private <T> Call<T> createCall(String key) {
        lock.lock();
        if (calls.containsKey(key)) {
            Call<T> c = calls.get(key);
            lock.unlock();
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

    private <T> void makeCall(Call<T> c, String key, Supplier<T> supplier) {
        c.setData(supplier.get());
        lock.lock();
        calls.remove(key);
        lock.unlock();
        // 放在remove后面是为了防止countDownLatch.await报错
        c.getCountDownLatch().countDown();
    }

    private static class Call<T> {
        private final CountDownLatch countDownLatch;
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

    public static void main(String[] args) {
        final ShareCalls shareCalls = new ShareCalls();
        final String a = shareCalls.call("a", () -> "a");
        System.out.println(a);
        Map<String, String> map = new HashMap();
        map.put("b", "b");
        final Map<String, String> b = shareCalls.call("a", () -> map);
        System.out.println(b);
    }
}
