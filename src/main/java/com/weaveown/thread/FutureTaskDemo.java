package com.weaveown.thread;

import java.util.concurrent.FutureTask;

/**
 * @author WeaveOwn
 */
public class FutureTaskDemo {

    public static void main(String[] args) throws Exception {
        FutureTask futureTask = new FutureTask(()->{
            int sum = 0;
            for (int i = 0; i < 10; i++) {
                Thread.sleep(100);
                sum += i;
            }
            return sum;
        });
        new Thread(futureTask).start();
        new Thread(()->{
            System.out.println("do other thing");
        }).start();
        System.out.println(futureTask.get());
    }
}
