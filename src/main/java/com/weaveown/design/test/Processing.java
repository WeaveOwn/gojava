package com.weaveown.design.test;

/**
 * @author wangwei
 * @date 2022/4/20
 */
public class Processing {
    public void run(AFactory factory) {
        final A instance = factory.getInstance();
        instance.call();
    }
}
