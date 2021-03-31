package com.weaveown.design.behavioral.delegate;

/**
 * @author wangwei
 * @date 2021/3/25
 */
public class EmployeeA implements IEmployee {
    @Override
    public void doing(String command) {
        System.out.println("I am A, 擅长写文档");
    }
}
