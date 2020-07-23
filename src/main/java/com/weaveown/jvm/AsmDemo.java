package com.weaveown.jvm;

/**
 * @author wangwei
 * @date 2020/7/12
 */
public class AsmDemo {
    public static void main(String[] args) {
        boolean flag = true;
        if (flag) System.out.println("Hello, Java!");
        if (flag == true) System.out.println("Hello, JVM!");
    }
}
