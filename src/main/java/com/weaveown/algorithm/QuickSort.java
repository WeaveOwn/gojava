package com.weaveown.algorithm;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangwei
 * @date 2020/7/2
 */
public class QuickSort {


    private ReentrantLock lock;

    public void pop() {
        final ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        reentrantLock.unlock();
    }


    public void push() {
        this.lock.lock();;
        this.lock.unlock();
    }
}
