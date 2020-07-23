package com.weaveown.design.structural.chain;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

/**
 * @author wangwei
 * @date 2020/6/21
 * @decription 责任链之拦截器
 */
interface Filter {
    Object filter(Object target, FilterChain filterChain);
}

class FilterA implements  Filter {
    @Override
    public Object filter(Object target, FilterChain filterChain) {
        System.out.println("FilterA");
        return filterChain.filter(target);
    }
}

class FilterB implements  Filter {
    @Override
    public Object filter(Object target, FilterChain filterChain) {
        System.out.println("FilterB");
        return target;
    }
}

class FilterC implements  Filter {
    @Override
    public Object filter(Object target, FilterChain filterChain) {
        System.out.println("FilterC");
        return target ;
    }
}

class FilterChain {
    List<Filter> filterList = Lists.newArrayList();
    Iterator<Filter> filterIterator;

    public void addFilter(Filter filter) {
        filterList.add(filter);
    }

    public Object filter(Object target) {
        if (filterIterator == null) {
            filterIterator = filterList.iterator();
        }
        if (filterIterator.hasNext()) {
            Filter next = filterIterator.next();
            next.filter(target, this);
        }
        return target;
    }
}

public class ChainFilterDemo {

    public static void main(String[] args) {
        FilterA filterA = new FilterA();
        FilterB filterB = new FilterB();
        FilterC filterC = new FilterC();

        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(filterA);
        filterChain.addFilter(filterB);
        filterChain.addFilter(filterC);
        filterChain.filter(new Object());
    }
}
