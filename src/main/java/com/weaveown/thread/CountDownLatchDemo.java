package com.weaveown.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author wangwei
 * @date 2020/4/22 21:26
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName());
            }).start();
        }
        System.out.println("等待完成");
        countDownLatch.await();
        System.out.println("end");
    }
}
