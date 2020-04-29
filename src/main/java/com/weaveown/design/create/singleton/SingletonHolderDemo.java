package com.weaveown.design.create.singleton;

/**
 * @author wangwei
 * @date 2019/10/15 17:22
 */
public class SingletonHolderDemo {
    /**
     * 静态内部类单例
     */
    private static class SingletonHolder{
        static {
            System.out.println("被调用");
        }
        private final static SingletonHolderDemo DEMO = new SingletonHolderDemo();
    }
    public static SingletonHolderDemo getInstance() {
        return SingletonHolder.DEMO;
    }

    private SingletonHolderDemo() {

    }

    public static void main(String[] args) {
        System.out.println("start");
        SingletonHolderDemo singletonHolderDemo = SingletonHolderDemo.getInstance();
    }
}
