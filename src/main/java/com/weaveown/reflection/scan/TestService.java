package com.weaveown.reflection.scan;

import com.weaveown.reflection.annotation.WController;

/**
 * @author wangwei
 * @date 2020/4/21 22:50
 */
@WController
public class TestService {

    public void hello() {
        System.out.println("hello");
    }
}
