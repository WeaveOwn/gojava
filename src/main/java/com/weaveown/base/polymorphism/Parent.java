package com.weaveown.base.polymorphism;

/**
 * @author wangwei
 * @date 2021/3/31
 */
public class Parent {
    public void say() {
        doSay();
    }

    protected void doSay() {
        System.out.println("i am parent");
    }
}
