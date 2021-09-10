package com.weaveown.thread;

import com.google.common.collect.Lists;

import java.util.concurrent.*;

/**
 * @author wangwei
 * @date 2021/7/16
 */
public class FuturePoolDemo {
    public static void main(String[] args) {
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        try {
            final Object o = executorService.invokeAny(Lists.newArrayList(() -> call()), 2, TimeUnit.SECONDS);
            System.out.println(o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    static String call() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "a";
    }
}
