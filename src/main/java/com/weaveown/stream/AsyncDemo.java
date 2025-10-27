package com.weaveown.stream;

import com.google.common.collect.Lists;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author wangwei
 * @date 2022/2/24
 */
public class AsyncDemo {
    static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(8);
        Lists.newArrayList("1", "2", "3", "4", "5", "6", "7", "8")
                .stream().map(e -> CompletableFuture.supplyAsync(() -> {
            System.out.println(e);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            return e;
        }, executorService)).map(future -> future.thenAccept(e -> {
            System.out.println(e);
        })).collect(Collectors.toList()).forEach(CompletableFuture::join);

        System.out.println("finish");

    }

    public static boolean print(String e) {
        System.out.println(e);
        return true;
    }
}
