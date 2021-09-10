package com.weaveown.lock;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author wangwei
 * @date 2021/6/8
 */
public class ShareCallsPractice<T> {
    public static void main(String[] args) throws InterruptedException {
        LongAdder longAdder = new LongAdder();
        final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        for (int i = 0; i < 1000; i++) {
            forkJoinPool.execute(() -> {
                longAdder.add(1);
            });
        }

        final ForkJoinPool forkJoinPool2 = ForkJoinPool.commonPool();
        for (int i = 0; i < 1000; i++) {
            forkJoinPool2.execute(() -> {
                longAdder.add(1);
            });
        }
        Thread.sleep(10000000);
    }

}
