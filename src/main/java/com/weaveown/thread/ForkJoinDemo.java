package com.weaveown.thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * fork join demo
 *
 * @author WeaveOwn
 */
public class ForkJoinDemo {
    public static void main(String[] args) throws Exception {
        long s = System.currentTimeMillis();
        System.out.println(sum(0, 1000000));
        long e = System.currentTimeMillis();
        System.out.println(e - s);
        long s1 = System.currentTimeMillis();
        ForkJoin forkJoin = new ForkJoin(0, 1000000);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future submit = forkJoinPool.submit(forkJoin);
        System.out.println(submit.get());
        long e1 = System.currentTimeMillis();
        System.out.println(e1 - s1);


    }

    private static int sum(int first, int last) {
        int sum = 0;
        for (int i = first; i <= last; i++) {
            sum += i;
        }
        return sum;
    }


}

class ForkJoin extends RecursiveTask<Integer> {
    private int first;
    private int last;
    private int threshold = 500000;

    public ForkJoin(int first, int last) {
        this.first = first;
        this.last = last;
    }

    @Override
    protected Integer compute() {
        int result = 0;
        if (last - first <= threshold) {
            for (int i = first; i <= last; i++) {
                result += i;
            }
        } else {
            int middle = (last - first) / 2;
            ForkJoin firstForkJoin = new ForkJoin(first, middle);
            ForkJoin lastForkJoin = new ForkJoin(middle + 1, last);
            firstForkJoin.fork();
            lastForkJoin.fork();
            result = firstForkJoin.join() + lastForkJoin.join();
        }
        return result;
    }
}