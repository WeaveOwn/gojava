package com.weaveown.thread;

import java.util.concurrent.*;

/**
 * 信号量demo
 *
 * @author WeaveOwn
 */
public class SemaphoreExample {
    public static void main(String[] args) {
        int semaphoreCount = 3;
        int threadCount = 10;
        Semaphore semaphore = new Semaphore(semaphoreCount);
        ExecutorService executorService = buildExecutor();
        for (int i = 0; i < threadCount; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName());
                    System.out.println(semaphore.availablePermits());
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });

        }
        executorService.shutdown();
    }

    private static ThreadPoolExecutor buildExecutor() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                10, 20, 60L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        executor.setThreadFactory(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {

                return new Thread(r, "myThread-" + r.hashCode());
            }
        });

        return executor;
    }
}
