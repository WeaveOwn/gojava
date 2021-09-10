package com.weaveown.base;

import java.util.stream.Stream;

/**
 * 策略枚举(多个枚举但只有两个或三个策略): 比如工资计算,工作日与节假日
 *
 * @author wangwei
 * @date 2021/6/8
 */
public enum StrategyEnum {
    MONDAY, TUESDAY, THURSDAY, WEEKEND(Type.FREE_DAY);

    private final Type type;

    StrategyEnum() {
        this(Type.WORK_DAY);
    }

    StrategyEnum(Type type) {
        this.type = type;
    }

    public void call() {
        this.type.call();
    }

    private enum Type {
        WORK_DAY() {
            @Override
            public void call() {
                System.out.println("work day");
            }
        },

        FREE_DAY() {
            @Override
            public void call() {
                System.out.println("free day");
            }
        };

        public abstract void call();
    }

    public static void main(String[] args) {
        Stream.of(StrategyEnum.values()).forEach(StrategyEnum::call);
    }

}
