package com.weaveown.stream;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Comparator;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * @author wangwei
 * @date 2020/7/13
 */
public class StreamDemo {
    /**
     * @param args
     */
    public static void main(String[] args) {
        String collect = Lists.newArrayList("1", "2", "3").stream().collect(Collectors.joining(","));
        System.out.println(collect);
    }

    public static void toMap() {
        Weave weave = Weave.builder().name("weave").age(24).build();
        Weave weave3 = Weave.builder().name("weave").age(25).build();
        Weave weave2 = Weave.builder().name("weave2").age(24).build();
        Map<String, Integer> collect = Lists.newArrayList(weave, weave2).stream().collect(Collectors.toMap(Weave::getName, Weave::getAge));
        Map<String, Long> collect2 = Lists.newArrayList(weave, weave2).stream().collect(Collectors.groupingBy(Weave::getName, Collectors.counting()));
        Map<String, Weave> collect1 = Lists.newArrayList(weave, weave2, weave3).stream().collect(Collectors.toMap(Weave::getName, a -> a,
                BinaryOperator.maxBy(Comparator.comparing(Weave::getAge))));
        System.out.println(collect);
        System.out.println(collect1);
    }

    @Data
    @Builder
    @Getter
    public static class Weave {
        private String name;
        private Integer age;
    }
}
