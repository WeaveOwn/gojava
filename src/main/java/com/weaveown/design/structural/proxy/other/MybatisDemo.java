package com.weaveown.design.structural.proxy.other;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author WeaveOwn
 */
public class MybatisDemo {
    static {
        System.out.println("staic");
    }

    {
        System.out.println("初始化");
    }

    public static void main(String[] args) throws Exception {
        Weave weave = (Weave) Proxy.newProxyInstance(MybatisDemo.class.getClassLoader(), new Class[]{Weave.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Select annotation = method.getAnnotation(Select.class);

                if (annotation != null) {
                    String value = annotation.value();
                    System.out.println(value);
                }


                return null;
            }
        });
        weave.print();
    }

}

