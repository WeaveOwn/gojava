package com.weaveown.thread;

/**
 * @author wangwei
 * @date 2020/11/19
 */
public class FinalReferenceExample {
    final int[] intArray;
    static FinalReferenceExample obj;

    public FinalReferenceExample() {
        this.intArray = new int[1];
        this.intArray[0] = 1;
    }

    public static void writeOne() {
        obj = new FinalReferenceExample();
    }

    public static void writeTwo() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        obj.intArray[0] = 2;
    }

    public static void reader() {
        if (obj != null) {
            int temp1 = obj.intArray[0];
            System.out.println(temp1);
        }
    }

    public static void main(String[] args) {
        new Thread(()->{
            writeOne();
        }).start();
        new Thread(()->{
            writeTwo();
        }).start();
        new Thread(()->{
            reader();
        }).start();
    }
}
