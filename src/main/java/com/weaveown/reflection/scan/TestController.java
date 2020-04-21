package com.weaveown.reflection.scan;

import com.weaveown.reflection.annotation.WAutoWired;
import com.weaveown.reflection.annotation.WController;

/**
 * @author wangwei
 * @date 2020/4/21 22:45
 */
@WController
public class TestController {

    @WAutoWired
    private TestService testService;

    public void hello() {
        testService.hello();;
    }
}
