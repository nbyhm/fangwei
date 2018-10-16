package com.dowell.annotation;

import java.lang.annotation.*;

/**
 * @author nanbo
 * @description 登录效验
 * @create 2018-10-02
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
}
