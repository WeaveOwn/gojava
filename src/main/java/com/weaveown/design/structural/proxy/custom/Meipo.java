package com.weaveown.design.structural.proxy.custom;

import java.lang.reflect.Method;

/**
 * @author wangwei
 * @date 2021/2/22
 */
public class Meipo implements WInvocationHandler {

    private Person person;

    public Object getObject(Person person) {
        this.person = person;
        Class<?> clazz = this.person.getClass();
        return WProxy.newProxyInstance(new WClassLoader(), clazz.getInterfaces(), this);

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("mei po start");
        return method.invoke(this.person, null);
    }
}
