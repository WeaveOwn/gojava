package com.weaveown.effective;

import java.util.Arrays;

/**
 * @author wangwei
 * @date 2020/6/11
 */
public class C37 {

    /**
     * 表示多个年份的不同的植物
     */
    private static class Plant {
        /**  生命周期 1年，两年，多年  **/
        enum LifeCycle {ONE, TWO, MORE};


        private LifeCycle lifeCycle;

        private String name;
        public Plant(LifeCycle lifeCycle, String name) {
            this.lifeCycle = lifeCycle;
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }
}
