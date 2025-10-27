package com.weaveown;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author WeaveOwn
 */
public class DailyPractice {
    static AtomicInteger sum = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        Thread.sleep(20000);
        final Runnable runnable = () -> {
            for (int i = 0; i < 1000000000; i++) {
                sum.getAndAdd(1);
            }
        };
        final Thread thread = new Thread(runnable);
        final Thread thread1 = new Thread(runnable);
        thread.start();
        thread1.start();
        Thread.sleep(2000);
        System.out.println("num = " + sum);
    }

}

