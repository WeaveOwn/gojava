package com.willvi.knowledge;

/**
 * @author WeaveOwn
 */
public class ShutDownHookDemo {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("do something")));

    }
}
