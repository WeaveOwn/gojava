package com.weaveown.thread.volatiles;

/**
 * @author wangwei
 * @date 2021/4/1
 */
public class VolatileDemo {
    int a = 0;
    volatile boolean flag = false;

    public void writer() {
        a = 1; // 1
        flag = true; // 2
    }

    public void reader() {
        if (flag) {
            // 3
            int i = a;
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        VolatileDemo volatileDemo = new VolatileDemo();
        new Thread(() -> {
            volatileDemo.reader();
        }).start();

        new Thread(() -> {
            volatileDemo.writer();
        }).start();
    }

}
