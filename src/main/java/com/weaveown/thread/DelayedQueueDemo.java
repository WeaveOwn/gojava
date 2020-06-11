package com.weaveown.thread;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author wangwei
 * @date 2020/5/29
 */
public class DelayedQueueDemo {
    public static void main(String[] args) throws Exception {
        DelayedItem item1 = new DelayedItem("item1", 10, TimeUnit.SECONDS);
        DelayedItem item2 = new DelayedItem("item2",5, TimeUnit.SECONDS);
        DelayedItem item3 = new DelayedItem("item3",15, TimeUnit.SECONDS);
        DelayQueue<DelayedItem> queue = new DelayQueue<>();
        queue.put(item1);
        queue.put(item2);
        queue.put(item3);
        System.out.println("begin time:" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        for (int i = 0; i < 3; i++) {
            DelayedItem take = queue.take();
            System.out.format("name:{%s}, time:{%s}\n",take.name, LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        }
    }



    static class DelayedItem implements Delayed {
        String name;
        private long expireTime;

        /**
         *
         * @param name
         * @param time 延迟时间
         * @param timeUnit
         */
        public DelayedItem(String name, long time, TimeUnit timeUnit) {
            this.name = name;
            this.expireTime = System.currentTimeMillis() + (time > 0? timeUnit.toMillis(time): 0);
        }

        @Override
        public long getDelay(TimeUnit unit) {
            // 被DelayQueue循环调用
            return expireTime - System.currentTimeMillis();
        }

        @Override
        public int compareTo(Delayed o) {
return 0;
//            DelayedItem item = (DelayedItem) o;
//
//            long diff = this.expireTime - item.expireTime;
//            if (diff <= 0) {// 改成>=会造成问题
//                return -1;
//            }else {
//                return 1;
//            }
        }
    }
}
