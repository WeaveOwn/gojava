package com.weaveown.design.create.factory.abstractfactory.dell;

import com.weaveown.design.create.factory.abstractfactory.Keybo;

/**
 * @author wangwei
 * @date 2019/10/12 15:46
 */
public class DellKeybo implements Keybo {
    @Override
    public void touch() {
        System.out.println("dell keybo");
    }
}
