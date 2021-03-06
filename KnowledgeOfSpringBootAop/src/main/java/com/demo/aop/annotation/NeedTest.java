package com.demo.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 揭光智
 * @date 2019/02/27
 */

@Retention(RetentionPolicy.RUNTIME)//声明该注解的保留期限
@Target(ElementType.METHOD)//声明可以使用该注解的目标类型
public @interface NeedTest {//定义注解
    boolean value() default true;//声明注解的成员属性
}
