package com.weaveown.base;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author wangwei
 * @date 2020/5/15
 */
public class Compare {
    public static void main(String[] args) {

        List<Integer> integers = Lists.newArrayList(1, 6, 2, 1, 25, 436, 233, 213, 125);
        Collections.sort(integers, (o1, o2) -> {
            // 第一个元素小于第二个元素 返回负数代表 不交换，返回 0 或 1 代表交换两者值
            if (o1 < o2) {
                return -1;
            }
            return 1;
        });
        System.out.println(integers);
    }
}
