package com.weaveown.stream;

import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangwei
 * @date 2021/6/15
 */
public class FlatMapDemo {
    public static void main(String[] args) {
        // flatMap 将所有集合流整合成一个流.
        final List<Integer> collect = Lists.newArrayList(Lists.newArrayList(1, 2), Lists.newArrayList(3, 4)).stream().flatMap(Collection::stream)
                .collect(Collectors.toList());
        System.out.println(collect);
    }
}
