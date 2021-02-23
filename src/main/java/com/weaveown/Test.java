package com.weaveown;

import lombok.Data;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.lang.instrument.Instrumentation;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.regex.Pattern;

/**
 * @author wangwei
 * @date 2020/5/28
 */
public class Test {

    private static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation inst) {
        instrumentation = inst;
    }

    public static long getObjectSize(Object o) {
        return instrumentation.getObjectSize(o);
    }

    public static void main(String[] args) {
        System.out.println(Test.class.getResource("").getPath());
    }
}
