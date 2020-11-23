package com.weaveown.jvm;

import java.lang.reflect.Method;

/**
 * @author wangwei
 * @date 2020/11/16
 */
public class Test {
    public static void target(int i) { // 空方法
    }

    public static void main(String[] args) throws Exception {
        Class klass = Class.forName("com.weaveown.jvm.Test");
        Method method = klass.getMethod("target", int.class);
        method.setAccessible(true);
        long current = System.currentTimeMillis();
        Integer integer = new Integer(128);
        for (int i = 1; i <= 2_000_000_000; i++) {
            if (i % 100_000_000 == 0) {
                long temp = System.currentTimeMillis();
                System.out.println(temp - current);
                current = temp;
            }
            method.invoke(null, integer);
        }
    }

}
