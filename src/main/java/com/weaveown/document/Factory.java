package com.weaveown.document;

/**
 * @author wangwei
 * @date 2022/5/6
 */
public class Factory {
    public static Validator get() {
        return new JsonValidator();
    }
}
