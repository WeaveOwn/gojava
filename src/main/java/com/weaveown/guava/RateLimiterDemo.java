package com.weaveown.guava;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author wangwei
 * @date 2021/6/11
 */
public class RateLimiterDemo {
    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = RateLimiter.create(5);
        System.out.println(limiter.acquire());
        System.out.println(limiter.acquire());
        System.out.println(limiter.acquire());
        System.out.println(limiter.acquire());
        System.out.println(limiter.acquire());
        System.out.println(limiter.acquire());
        System.out.println(limiter.acquire());
    }
}
