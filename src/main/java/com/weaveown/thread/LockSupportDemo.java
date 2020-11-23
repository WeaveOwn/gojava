package com.weaveown.thread;


/**
 * @author wangwei
 * @date 2020/6/21
 */
public class LockSupportDemo {
    static Lock lock = new Lock();
    static int counter = 0;

    public static void foo() {
        synchronized (lock) {
            counter++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        lock.hashCode(); // Step 2
        System.identityHashCode(lock); // Step 4
        for (int i = 0; i < 1_000_000; i++) {
            foo();
        }
    }

    static class Lock {
        @Override
        public int hashCode() {
            return 0;
        } // Step 3 }
    }
}
