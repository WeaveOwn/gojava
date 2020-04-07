package com.weaveown.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author WeaveOwn
 */
public class BlockingQueueDemo {
    private static BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(5);

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Producer().start();
        }
        for (int i = 0; i < 5; i++) {
            new Consumer().start();
        }
        for (int i = 0; i < 3; i++) {
            new Producer().start();
        }
    }

    static class Producer extends Thread {
        @Override
        public void run() {
            try {
                blockingQueue.put("product");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("produce..");
        }
    }

    static class Consumer extends Thread {
        @Override
        public void run() {
            try {
                blockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("consume..");
        }
    }
}
