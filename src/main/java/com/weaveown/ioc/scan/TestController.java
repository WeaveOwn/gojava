package com.weaveown.ioc.scan;

import com.weaveown.ioc.annotation.WAutoWired;
import com.weaveown.ioc.annotation.WController;

/**
 * @author wangwei
 * @date 2020/4/21 22:45
 */
@WController
public class TestController {

    @WAutoWired
    private TestService testService;

    private String a;

    public void hello() {
        testService.hello();
    }
}
