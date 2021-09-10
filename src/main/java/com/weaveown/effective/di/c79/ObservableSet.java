package com.weaveown.effective.di.c79;

import com.weaveown.effective.ForwardingSet;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 可优化为无锁或者说是代码看不见同步方法的调用(使用CopyOnWriteArrayList)
 *
 * @author wangwei
 * @date 2021/7/9
 */
public class ObservableSet<E> extends ForwardingSet<E> {

    public ObservableSet(Set<E> set) {
        super(set);
    }

    private final List<SetObserver<E>> observers
            = new ArrayList<>();

    public void addObserver(SetObserver<E> observer) {
        synchronized (observers) {
            observers.add(observer);
        }
    }

    public boolean removeObserver(SetObserver<E> observer) {
        synchronized (observers) {
            return observers.remove(observer);
        }
    }

    private void notifyElementAdded(E element) {
        synchronized (observers) {
            for (SetObserver<E> observer : observers)
                observer.added(this, element);
        }
    }

    @Override
    public boolean add(E element) {
        boolean added = super.add(element);
        if (added)
            notifyElementAdded(element);
        return added;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean result = false;
        for (E element : c)
            result |= add(element);  // Calls notifyElementAdded
        return result;
    }

    public static void main(String[] args) {

        deadLock();
    }

    static void deadLock() {
        ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());
        set.addObserver(
                new SetObserver<Integer>() {
                    @Override
                    public void added(ObservableSet<Integer> s, Integer e) {
                        System.out.println(e);
                        if (e == 23) {
                            ExecutorService exec = Executors.newSingleThreadExecutor();
                            try {
                                // get等待线程结果,而线程等待锁.造成了死锁.
                                exec.submit(() -> s.removeObserver(this)).get();
                            } catch (ExecutionException | InterruptedException ex) {
                                throw new AssertionError(ex);
                            } finally {
                                exec.shutdown();
                            }
                        }
                    }
                }
        );

        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
    }

    static void concurrentModException() {
        ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());
        set.addObserver(
                new SetObserver<Integer>() {
                    @Override
                    public void added(ObservableSet<Integer> s, Integer e) {
                        System.out.println(e);
                        if (e == 23) {
                            s.removeObserver(this);
                        }
                    }
                }
        );

        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
    }
}
