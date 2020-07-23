package com.weaveown.design.structural.proxy.other;

import com.weaveown.design.structural.proxy.other.Select;

/**
 * @author wangwei
 * @date 2020/7/22
 */
public interface Weave {
    @Select("select * from weave")
    void  print();
}
