package com.weaveown.cls;

public class ClassLoadDemo {
    public static void main(String[] args) {
        System.out.println(MyClass.i);
        new MyClassChild();
    }
}
