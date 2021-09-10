package com.weaveown.cache;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangwei
 * @date 2021/5/31
 */
public class SetBlockingQueue<E> {
    private Set<E> queue;
    private ReentrantLock lock;
    private Condition notEmpty;
    private Condition notFull;
    private int maxSize;


    public SetBlockingQueue() {
        this.queue = new HashSet<>();
        this.lock = new ReentrantLock();
        this.notEmpty = lock.newCondition();
        this.notFull = lock.newCondition();
    }

    public SetBlockingQueue(int maxSize) {
        this();
        this.maxSize = maxSize;


    }

    public boolean add(E e) {
        checkNotNull(e);
        ReentrantLock lock = this.lock;
        lock.lock();
        try {
            if (maxSize != 0) {
                if (maxSize == queue.size()) {
                    return false;
                }
            }
            enqueue(e);
            return true;
        } finally {
            lock.unlock();
        }
    }

    public void put(E e) throws InterruptedException {
        checkNotNull(e);
        ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            if (maxSize != 0) {
                if (maxSize == queue.size()) {
                    notFull.await();
                }
            }

            enqueue(e);
        } finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        ReentrantLock lock = this.lock;
        lock.lockInterruptibly();

        try {
            while (queue.isEmpty()) {
                notEmpty.await();
            }

            return dequeue();
        } finally {
            lock.unlock();
        }

    }

    public E poll() {
        ReentrantLock lock = this.lock;
        lock.lock();
        try {
            if (queue.isEmpty()) {
                return null;
            }
            return dequeue();
        } finally {
            lock.unlock();
        }

    }

    private void enqueue(E e) {
        final boolean add = queue.add(e);
        if (add) {
            notEmpty.signal();
        }
    }

    private E dequeue() {
        final E next = queue.iterator().next();
        queue.remove(next);
        notFull.signal();
        return next;
    }

    private static void checkNotNull(Object v) {
        if (v == null)
            throw new NullPointerException();
    }
}
