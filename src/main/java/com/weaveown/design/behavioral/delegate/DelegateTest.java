package com.weaveown.design.behavioral.delegate;

/**
 * 静态代理模式的特殊模式 全权代理. 只关心结果不关心过程.不做增强功能,主要做分发功能,比如DispatchServlet spring mvc的分发器
 * Manager就是所谓的委派类,boss全权委托manager来处理问题
 *
 * @author wangwei
 * @date 2021/3/25
 */
public class DelegateTest {
    public static void main(String[] args) {
        new Boos().command("文档", new Manager());
    }
}
