package com.weaveown.effective;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author wangwei
 * @date 2020/4/22 16:58
 */
public class Chapter4 {
    /** 公有数组或者可变对象的静态域正确表达方法 */
    private static final Map[] PRIVATE_MAPS =  {};
    public static final List<Map> MAPS = Collections.unmodifiableList(Arrays.asList(PRIVATE_MAPS));
    /** 每次返回他的拷贝，也可以做到安全 */
    public static final  Map[] MAPS1 = PRIVATE_MAPS.clone();
}
