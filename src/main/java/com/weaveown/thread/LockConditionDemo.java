package com.weaveown.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangwei
 * @date 2020/5/17
 */
public class LockConditionDemo {
    private Lock lock = new ReentrantLock();
    private Condition notFullCondition = lock.newCondition();
    private Condition notEmptyCondition = lock.newCondition();
}
