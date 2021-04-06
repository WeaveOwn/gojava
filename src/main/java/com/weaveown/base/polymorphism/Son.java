package com.weaveown.base.polymorphism;

/**
 * @author wangwei
 * @date 2021/3/31
 */
public class Son extends Parent {

    public void call() {
        super.say();
        ;
    }

    @Override
    protected void doSay() {
        System.out.println("i am son");
    }

    public static void main(String[] args) {
        new Son().call();
    }
}
