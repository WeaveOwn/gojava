package com.weaveown.jmh;

import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collection;
import java.util.stream.IntStream;

/**
 * @author wangwei
 * @date 2020/7/14
 */
public class JmhDemo {
    public static void main(String[] args) throws Exception {
        /*
          Options opt = new OptionsBuilder()
                // 导入要测试的类
                .include(StringJoinBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
         */
        Options opt = new OptionsBuilder()
                // 导入要测试的类
                .include(StringJoinBenchmark.class.getSimpleName())
                // 预热2轮
                .warmupIterations(2)
                // 度量2轮
                .measurementIterations(2)
                .threads(2)
                .mode(Mode.Throughput)
                .forks(2)
                .build();

        Collection<RunResult> run = new Runner(opt).run();

    }
}
