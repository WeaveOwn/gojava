package com.weaveown.design.test;

/**
 * @author wangwei
 * @date 2022/4/20
 */
public class Main {
    public static void main(String[] args) {
        AFactoryImpl aFactory = new AFactoryImpl();
        new Processing().run(aFactory);
    }
}
