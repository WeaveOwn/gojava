package com.weaveown.thread;

import java.util.concurrent.*;

/**
 * 接口超时调用
 *
 * @author wangwei
 * @date 2021/6/3
 */
public class CallUtils {
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);


    public static <T> T execute(Callable<T> callable) {
        final Future<T> future = executorService.submit(callable);
        try {
            return future.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            future.cancel(true);
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(0x01);
        CallUtils.execute(() -> {
            System.out.println("start");
            Thread.sleep(1010);
            System.out.println("end");
            return "hello";
        });

    }
}
