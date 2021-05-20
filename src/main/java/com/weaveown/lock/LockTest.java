package com.weaveown.lock;

import lombok.Getter;
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
        System.out.println(t);
        System.out.println(tail);
        // 改变的是this本身域的值, 而t不属于实例域只是指向了tail,所以最终改变的是tail,对应tailOffset,因为上面获取的偏移量是针对tail的
        unsafe.compareAndSwapObject(this, tailOffset, t, node2);
        System.out.println(tail.name);
        System.out.println(t.name);
    }

    public static void main(String[] args) {
//        new Thread(() -> lock.lock()).start();
//        new Thread(() -> lock.lock()).start();
        new LockTest().test();

    }

    @Getter
    static class Node {
        private Node next;
        private Node pre;
        private String name;

        public Node(String name) {
            this.name = name;
        }
    }
}
