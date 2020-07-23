package com.weaveown.jmh;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.apache.tools.ant.util.StringUtils;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author wangwei
 * @date 2020/7/14
 */

public class StringJoinBenchmark {
    @Benchmark
    public void testStringBuilder() {
        print(new StringBuilder().append(1).append(2).append(3).toString());
    }

    @Benchmark
    public void testStringAdd() {
        print(new String()+ 1 + 2 + 3);
    }

    public void print(String str) {

    }
}
