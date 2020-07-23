package com.weaveown.ioc.annotation;

import java.lang.annotation.*;

/**
 * @author wangwei
 * @date 2020/4/21 22:51
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WAutoWired {
    String value() default "";
}
