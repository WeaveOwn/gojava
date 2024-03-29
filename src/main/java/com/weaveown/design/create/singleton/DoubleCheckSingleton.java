package com.weaveown.design.create.singleton;

/**
 * @author wangwei
 * @date 2019/10/9 11:14
 */
public class DoubleCheckSingleton {
    /**
     * 饿汉式（双重检验）
     */
    private transient volatile DoubleCheckSingleton singleton = null;

    private DoubleCheckSingleton() {

    }

    public DoubleCheckSingleton getSingleton() {
        if (singleton == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (singleton == null) {
                    singleton = new DoubleCheckSingleton();
                }
            }
        }
        return singleton;
    }
}
