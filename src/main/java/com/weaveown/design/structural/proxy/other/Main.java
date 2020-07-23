package com.weaveown.design.structural.proxy.other;

import com.weaveown.DailyPractice;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wangwei
 * @date 2020/7/22
 */
public class Main {
    public static void main(String[] args) {
        Weave weave = (Weave) Proxy.newProxyInstance(DailyPractice.class.getClassLoader(), new Class[]{Weave.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Select annotation = method.getAnnotation(Select.class);

                if (annotation != null){
                    String value = annotation.value();
                    System.out.println(value);
                }


                return null;
            }
        });
        weave.print();
    }
}
