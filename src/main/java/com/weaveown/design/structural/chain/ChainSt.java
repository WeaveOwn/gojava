package com.weaveown.design.structural.chain;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author wangwei
 * @date 2020/8/20
 */
interface FilterSt {
    void doFilter();
}

class FilterStA implements FilterSt {
    @Override
    public void doFilter() {
        System.out.println("A");
    }
}
class FilterStB implements FilterSt {
    @Override
    public void doFilter() {
        System.out.println("B");
    }
}
class Chain implements FilterSt {
    List<FilterSt> filterStList = Lists.newArrayList();
    public void addFilter(FilterSt st) {
        this.filterStList.add(st);
    }
    @Override
    public void doFilter() {
        for (FilterSt filterSt : filterStList) {
            filterSt.doFilter();
        }
    }
}
public class ChainSt {
    public static void main(String[] args) {
        Chain chain = new Chain();
        chain.addFilter(new FilterStB());
        chain.addFilter(new FilterStA());
        chain.doFilter();
    }
}
