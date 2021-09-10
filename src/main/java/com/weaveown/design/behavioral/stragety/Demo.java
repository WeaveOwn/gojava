package com.weaveown.design.behavioral.stragety;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangwei
 * @date 2021/9/10
 */
public class Demo {

    interface A {
        void call();
    }

    static class B implements A {
        @Override
        public void call() {
            System.out.println("B");
        }
    }

    static class C implements A {
        @Override
        public void call() {
            System.out.println("C");
        }
    }

    /**
     * 优化前,包含多个if-else
     */
    static class IfElseAFactory {
        static Map<String, A> map = new HashMap<>();

        static {
            map.put("B", new B());
            map.put("C", new C());
        }

        static A get(int other) {
            if (other < 5) {
                return map.get("B");
            } else {
                return map.get("C");

            }
        }
    }

    /**
     * 优化后减少if-else
     */
    static class AFactory {
        static List<ARange> list = new ArrayList<>();

        static {
            list.add(new ARange(0, 5, new B()));
            list.add(new ARange(6, 100, new C()));
        }

        static A get(int other) {
            for (ARange aRange : list) {
                if (aRange.inRange(other)) {
                    return aRange.get();
                }
            }
            return null;
        }

        static class ARange {
            int min;
            int max;
            A a;

            public ARange(int min, int max, A a) {
                this.min = min;
                this.max = max;
                this.a = a;
            }

            public A get() {
                return a;
            }

            public boolean inRange(int value) {
                return value >= min && value <= max;
            }
        }
    }
}
