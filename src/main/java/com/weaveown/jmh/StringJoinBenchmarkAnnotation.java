package com.weaveown.jmh;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * @author wangwei
 * @date 2020/7/14
 */
@BenchmarkMode({Mode.Throughput})
@Warmup(iterations = 2)
@Measurement(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(2)
@Fork(2)
public class StringJoinBenchmarkAnnotation {
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
