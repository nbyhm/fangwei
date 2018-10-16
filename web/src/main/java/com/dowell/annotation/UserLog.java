package com.dowell.annotation;

import java.lang.annotation.*;

/**
 * @author nanbo
 * @description 用户日志注解
 * @create 2018-10-05
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserLog {

	String value() default "";
}
