package com.weaveown.stream;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author wangwei
 * @date 2021/6/15
 */
public class MapComputeIfPresent {

    public static void main(String[] args) {
        Map<String, Set<String>> map = new HashMap<>();
        // key不存在的话,put进执行func初始化得到的一个值
        map.computeIfAbsent("weave", set -> new HashSet<>()).add("weave");

        final T t = new T();
        Lists.newArrayList(1, 2, 3, 4).forEach(e -> {
            add(t);
            System.out.println(t.i);
        });

    }

    public static void add(T t) {
        t.i++;
    }


    static class T {
        public int i;
    }
}
