package com.weaveown.base;

/**
 * @author wangwei
 * @date 2020/5/25
 */
public class NestedClassDemo {

    public static void main(String[] args) {
        new NestedClassDemo("1", "2").say();

    }

    private String a;
    private String b;

    public NestedClassDemo(String a , String b) {
        this.a = a;
        this.b = b;
    }

    public void say() {
        new NestedClass().nestedClassHello();
    }

    public void hello() {
        System.out.println(a + b);
    }

    private class NestedClass {
        public void nestedClassHello() {
            hello();
        }
    }
}
