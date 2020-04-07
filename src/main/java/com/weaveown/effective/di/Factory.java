package com.weaveown.effective.di;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class Factory implements Supplier {

    @Override
    public Object get() {
        Map<String, String> map = new HashMap<>();
        map.keySet();
//        CountDownLatch
        Maps.newHashMap().keySet();

        return null;
    }
}
