package com.weaveown.design.structural.proxy.custom;

import java.lang.reflect.Method;

/**
 * @author wangwei
 * @date 2021/2/22
 */
public interface WInvocationHandler {
    Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;
}
