package com.weaveown.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangwei
 * @date 2020/5/17
 */
public class LockConditionDemo {
    private static Lock lock = new ReentrantLock();
    private static Condition notFullCondition = lock.newCondition();
    private static Condition notEmptyCondition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        lock.lock();
        notEmptyCondition.await();
        lock.unlock();
    }
}
