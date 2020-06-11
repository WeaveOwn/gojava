package com.weaveown.rxjava;

import reactor.core.Disposable;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author wangwei
 * @date 2020/5/21
 */
public class Test {

    public static void main(String[] args) throws Exception {
        Map<String , String> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("1","1");
        Mono<Map<String, String>> just = Mono.just(objectObjectHashMap);

        just.subscribe(System.out::println);
        just.subscribe(System.out::println);
        Thread.sleep(10000);
    }

    public static void hello(List<Integer> integer) {
        System.out.println(Thread.currentThread().getName() + "   " + integer);
    }

    public static void get() {
        Flux<Integer> just = Flux.just(1);
        long s = System.currentTimeMillis();
        Integer[] a = new Integer[]{1,2,3,4,5,6,7,8,9,10,100};
        Flux.fromArray(a).buffer(3)
//                .doOnNext(com.weaveown.Test::hello)
                .doOnComplete(()->{
                    System.out.println("finsh");
                })
//                .subscribeOn(Schedulers.elastic())
                .subscribe(new BaseSubscriber<List<Integer>>() {
                    @Override
                    protected void hookOnNext(List<Integer> value) {
                        System.out.println(Thread.currentThread().getName() + "   " + value);
                    }
                });
        long e = System.currentTimeMillis();
    }
}
