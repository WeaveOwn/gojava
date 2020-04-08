package com.weaveown.cls;

/**
 * @author WeaveOwn
 */
public class MyClass {
    static {
        System.out.println("准备");
    }
    {
        System.out.println("初始化");
    }
    public static int i = 123;
}
