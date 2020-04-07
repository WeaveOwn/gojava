package com.weaveown.design.create.factory.abstractfactory.hp;

import com.weaveown.design.create.factory.abstractfactory.AbstractPcFactory;
import com.weaveown.design.create.factory.abstractfactory.Keybo;
import com.weaveown.design.create.factory.abstractfactory.Mouse;

/**
 * @author wangwei
 * @date 2019/10/12 15:43
 */
public class HpFactory extends AbstractPcFactory {
    @Override
    public Keybo getKeybo() {
        return new HpKeybo();
    }

    @Override
    public Mouse getMouse() {
        return new HpMouse();
    }
}
