package com.weaveown.base;

/**
 * @author wangwei
 * @date 2021/10/2
 */
public class InnerClassDemo {
    private String a;
    private String b;

    public void call() {
        this.a = "a";
        this.b = "b";
        final C c = new C();
        c.call();
        this.a = "b";
        final C c1 = new C();
        c1.call();
    }


    private class C {
        private String c;

        public void call() {
            System.out.println(a);
        }
    }

    public static void main(String[] args) {
        new InnerClassDemo().call();
    }
}
