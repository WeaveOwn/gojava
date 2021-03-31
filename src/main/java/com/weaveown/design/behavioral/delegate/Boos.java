package com.weaveown.design.behavioral.delegate;

/**
 * @author wangwei
 * @date 2021/3/25
 */
public class Boos {
    public void command(String command, Manager manager) {
        manager.doing(command);
    }
}
