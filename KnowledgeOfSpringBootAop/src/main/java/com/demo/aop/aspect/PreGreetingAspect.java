package com.demo.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 通过{@link Aspect}注解,表示PreGreetingAspect是一个切片
 * 通过{@link Component}注解,把这个切面Bean交给Spring容器管理,如果没有这个注解,aop将不会生效
 *
 * @author 揭光智
 * @date 2019/02/27
 */
@Aspect
@Component
public class PreGreetingAspect {

    /**
     * {@link Before}前置增强
     * "execution(* greetTo(..))" 切点表达式
     * execution：关键字,execution()函数    * greetTo(..) 匹配模式串,函数的入参
     * System.out.println("How are you?"); 横切逻辑
     */
    @Before("execution(* greetTo(..))")
    public void beforeGreeting() {
        System.out.println("How are you?");
    }
}
