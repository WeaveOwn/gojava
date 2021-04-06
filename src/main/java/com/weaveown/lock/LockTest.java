package com.weaveown.lock;

import lombok.Data;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangwei
 * @date 2021/4/1
 */
public class LockTest {
    private static ReentrantLock lock = new ReentrantLock();
    private static Unsafe unsafe;
    private static final long tailOffset;
    private transient volatile Node tail;

    static {
        Class<Unsafe> unsafeClass = Unsafe.class;
        try {
            Field theUnsafe = unsafeClass.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
            tailOffset = unsafe.objectFieldOffset(LockTest.class.getDeclaredField("tail"));
        } catch (Exception e) {
            throw new Error(e);
        }

    }

    public void test() {
        Node node2 = new Node("2");

        Node node = new Node("1");
        tail = node;
        Node t = tail;
//        unsafe.compareAndSwapObject(this, tailOffset, t, node2);
        tail = node2;
        System.out.println(tail.name);
        System.out.println(t.name);
    }

    public static void main(String[] args) {
        new Thread(() -> lock.lock()).start();
        new Thread(() -> lock.lock()).start();
        new LockTest().test();

    }

    @Data
    static class Node {
        private Node next;
        private Node pre;
        private String name;

        public Node(String name) {
            this.name = name;
        }
    }
}
