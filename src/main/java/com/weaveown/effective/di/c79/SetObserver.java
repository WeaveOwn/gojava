package com.weaveown.effective.di.c79;

/**
 * @author wangwei
 * @date 2021/7/9
 */
@FunctionalInterface
public interface SetObserver<E> {
    void added(ObservableSet<E> set, E element);
}
