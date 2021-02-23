package com.weaveown.design.structural.proxy.custom;

import java.lang.reflect.Proxy;

/**
 * @author wangwei
 * @date 2021/2/22
 */
public class Girl implements Person {
    @Override
    public void find() {
        System.out.println("paopao want find weaveown");
    }
}
