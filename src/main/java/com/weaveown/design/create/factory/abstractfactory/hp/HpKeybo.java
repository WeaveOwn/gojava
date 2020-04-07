package com.weaveown.design.create.factory.abstractfactory.hp;

import com.weaveown.design.create.factory.abstractfactory.Keybo;

/**
 * @author wangwei
 * @date 2019/10/12 15:43
 */
public class HpKeybo implements Keybo {
    @Override
    public void touch() {
        System.out.println("hp keybo");
    }
}
