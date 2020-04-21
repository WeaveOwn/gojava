package com.weaveown.reflection.annotation;

import java.lang.annotation.*;

/**
 * @author wangwei
 * @date 2020/4/21 22:47
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WController {
    String value() default  "";
}
