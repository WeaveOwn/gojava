package com.weaveown.design.structural.proxy.custom;

/**
 * 手撸简单的动态代理
 *
 * @author wangwei
 * @date 2021/2/22
 */
public class WProxyTest {
    // 代理基本流程
    // Proxy.newInstance()
    // 1. 生成一个继承被代理接口的$Proxy0并依赖InvocationHandler接口的类. 每个方法里面通过调用InvocationHandler.invoke()来实现
    // 2. 通过classLoader加载$Proxy0类.
    // 3. 通过构造器反射传入实现了InvocationHandler接口的实例,实例化$Proxy0
    public static void main(String[] args) throws Exception {
        Person person = (Person) new Meipo().getObject(new Girl());
        person.find();
    }
}
