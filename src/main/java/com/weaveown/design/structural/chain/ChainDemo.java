package com.weaveown.design.structural.chain;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author wangwei
 * @date 2020/6/21
 * 责任链模式
 */
interface Plugin {
    Object plugin(Object target);
}

class PluginA implements  Plugin {
    @Override
    public Object plugin(Object target) {
        System.out.println("PluginA");
        return target;
    }
}
class PluginB implements  Plugin {
    @Override
    public Object plugin(Object target) {
        System.out.println("PluginB");
        return target;
    }
}
class PluginC implements  Plugin {
    @Override
    public Object plugin(Object target) {
        System.out.println("PluginC");
        return target;
    }
}

class PluginChain {
    List<Plugin> pluginList = Lists.newArrayList();

    public void addPlugin(Plugin plugin) {
        pluginList.add(plugin);
    }

    public void pluginAll(Object target) {
        for (Plugin plugin : pluginList) {
            plugin.plugin(target);
        }
    }
}

public class ChainDemo {

    public static void main(String[] args) {
        PluginA pluginA = new PluginA();
        PluginB pluginB = new PluginB();
        PluginC pluginC = new PluginC();

        PluginChain pluginChain = new PluginChain();
        pluginChain.addPlugin(pluginA);
        pluginChain.addPlugin(pluginB);
        pluginChain.addPlugin(pluginC);
        pluginChain.pluginAll(new Object());
    }
}
