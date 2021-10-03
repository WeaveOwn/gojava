package com.weaveown.design.base;

/**
 * 为何再次定义log id接口.一: 如果直接实现IdGenerator接口,那后续如果要支持两个生成规则,该如何命名. 二: 业务上应该隔离,不同业务是不同的生成规则.
 *
 * @author wangwei
 * @date 2021/10/3
 */
public interface LogIdGenerator extends IdGenerator {
}
