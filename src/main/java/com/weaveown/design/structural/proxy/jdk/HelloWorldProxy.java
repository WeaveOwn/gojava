package com.weaveown.design.structural.proxy.jdk;

import java.lang.reflect.Proxy;

/**
 * @author wangwei
 * @date 2021/11/18
 */
public class HelloWorldProxy {
    private Object target;

    public HelloWorldProxy(Object target) {
        this.target = target;
    }

    public Object getInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), (proxy, method, args) -> {
            System.out.println("Proxy start");
            method.invoke(target, args);
            System.out.println("Proxy end");
            return null;
        });
    }

    public static void main(String[] args) {
        IHelloWorld helloWorld = new HelloWorldImpl();
        IHelloWorld proxy = (IHelloWorld) new HelloWorldProxy(helloWorld).getInstance();
        proxy.sayHello();
    }
}
