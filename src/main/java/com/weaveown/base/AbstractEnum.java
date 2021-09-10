package com.weaveown.base;

import java.util.stream.Stream;

/**
 * @author wangwei
 * @date 2021/6/8
 */
public enum AbstractEnum {
    WEAVE_OWN() {
        @Override
        public void call() {
            System.out.println(this.name());
        }
    },
    WX() {
        @Override
        public void call() {
            System.out.println(this.name());
        }
    };

    /**
     * 基于抽象方法的enum
     */
    public abstract void call();

    public static void main(String[] args) {
        Stream.of(AbstractEnum.values()).forEach(AbstractEnum::call);
    }
}
