package com.weaveown.lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;

/**
 * @author wangwei
 * @date 2020/6/21
 * @decription 偏移锁
 */
public class NickleLock {
    private static Unsafe unsafe;
    private static long valueOffset;

    static {
        Class<Unsafe> unsafeClass = Unsafe.class;
        try {
            Field theUnsafe = unsafeClass.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
            valueOffset= unsafe.objectFieldOffset(NickleLock.class.getDeclaredField("value"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private volatile int value = 0;

    public void lock() {
        for (; ; ) {
            if (unsafe.compareAndSwapInt(this, valueOffset, 0, 1)) {
                return;
            }
            Thread.yield();
        }
    }

    public void unlock() {
        value = 0;
    }
    static NickleLock nickleLock = new NickleLock();
    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(()->{
            nickleLock.lock();
            try {
                System.out.println("first");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            nickleLock.unlock();
            countDownLatch.countDown();
        }).start();
        new Thread(()->{
            nickleLock.lock();
            try {
                System.out.println("second");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            nickleLock.unlock();
            countDownLatch.countDown();
        }).start();
        countDownLatch.await();
    }

}
