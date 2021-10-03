package com.weaveown.base;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

/**
 * weak reference 引用强引用时,不会被回收. 但如果有强引用引用WeakReference时还是被回收.ThreadLocal里面用的就是WeakReference只要不把ThreadLocal = null就不会被释放资源的.
 *
 * @author wangwei
 * @date 2021/4/6
 */
public class WeakReferenceDemo {

    private static ReferenceQueue<VeryBig> referenceQueue = new ReferenceQueue<>();

    private static void checkQueue() {
        Reference<? extends VeryBig> poll = null;

        while (((poll = referenceQueue.poll()) != null)) {
            System.out.println("In queue:" + ((VeryBigWeakReference) (poll)).getId());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<VeryBigWeakReference> references = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            references.add(new VeryBigWeakReference(new VeryBig(i), referenceQueue));
            System.out.println("create weak:" + i);
        }

        System.gc();

        Thread.sleep(10000);
        checkQueue();
        for (VeryBigWeakReference reference : references) {
            System.out.println(reference.getId());
        }
    }

    private static class VeryBig {
        private int id;


        public int getId() {
            return id;
        }

        // 被回收对象
        private byte[] b = new byte[2048];

        public VeryBig(int id) {
            this.id = id;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("finalize weak:" + this.id);
        }
    }

    private static class VeryBigWeakReference extends WeakReference<VeryBig> {

        public int id;


        public VeryBigWeakReference(VeryBig referent, ReferenceQueue<? super VeryBig> q) {
            super(referent, q);
            this.id = referent.getId();
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("finalize weakReference:" + id);
        }

        public int getId() {
            return id;
        }
    }
}
