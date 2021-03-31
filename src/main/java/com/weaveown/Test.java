package com.weaveown;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author wangwei
 * @date 2020/5/28
 */
public class Test {
    private static <K, T> Map<K, T> list2Map(List<T> data, Function<T, K> function) {
        return data.stream().collect(Collectors.toMap(function, item -> item, (k1, k2) -> k2));
    }


    public static void main(String[] args) {
        A a = new A();
        a.setA("a1");
        a.setB("a1");
        A b = new A();
        b.setA("b1");
        b.setB("b1");
        Map<String, A> map = list2Map(Lists.newArrayList(a, b), t -> t.getA());
        System.out.println(map);
        ArrayList<String> strings = Lists.newArrayList("123");
        ArrayList<String> strings1 = Lists.newArrayList("234");
        List<String> collect = Lists.newArrayList(strings, strings1).stream().flatMap(e -> e.stream()).collect(Collectors.toList());

    }

    /**
     * 分批查询
     *
     * @param keys      查询参数
     * @param size      每批大小
     * @param queryFunc 查询函数
     * @param executor  并行调度器(不传时,取系统默认的{@link ForkJoinPool#commonPool()}})
     * @param <T>       数据类型
     * @return
     */
    private static <K, T> List<T> batchQuery(List<K> keys, int size, Function<List<K>, List<T>> queryFunc, Executor executor) {
        if (size <= 0) {
            throw new IllegalArgumentException("The size must be positive");
        }

        if (CollectionUtil.isEmpty(keys)) {
            return Collections.emptyList();
        }

        Executor actualExecutor = Optional.ofNullable(executor).orElseGet(ForkJoinPool::commonPool);
        List<CompletableFuture<List<T>>> futures =
                Lists.partition(keys, size).stream()
                        .distinct()
                        .map(batchKeys -> CompletableFuture.supplyAsync(() -> queryFunc.apply(batchKeys), actualExecutor))
                        .collect(Collectors.toList());
        return futures.stream().map(CompletableFuture::join).flatMap(Collection::stream).collect(Collectors.toList());
    }


    @Data
    public static class A {
        private String a;
        private String b;

        public String get() {
            return "1";
        }
    }

}
