package com.weaveown.document;

/**
 * @author wangwei
 * @date 2022/5/6
 */
public class FactoryUML {

    public void call() {
        final Validator validator = Factory.get();
        validator.call();
    }
}
