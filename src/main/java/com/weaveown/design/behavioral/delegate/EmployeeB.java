package com.weaveown.design.behavioral.delegate;

/**
 * @author wangwei
 * @date 2021/3/25
 */
public class EmployeeB implements IEmployee {
    @Override
    public void doing(String command) {
        System.out.println("I am B, 擅长数据库");
    }
}
