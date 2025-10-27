package com.weaveown.design.test;

/**
 * @author wangwei
 * @date 2022/4/20
 */
public class AFactoryImpl implements AFactory {
    @Override
    public A getInstance() {
        return new A();
    }
}
