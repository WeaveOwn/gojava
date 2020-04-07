package com.weaveown.design.create.factory;

/**
 * @author wangwei
 * @date 2019/10/12 15:11
 */
public class HttpProtocol implements NetProtocol {
    @Override
    public void transfer() {
        System.out.println("HTTP");
    }
}
