package com.weaveown.design.behavioral.delegate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangwei
 * @date 2021/3/25
 */
public class Manager {
    private Map<String, IEmployee> register = new HashMap<>();

    public Manager() {
        register.put("文档", new EmployeeA());
        register.put("数据", new EmployeeB());
    }

    public void doing(String command) {
        register.get(command).doing(command);
    }
}
