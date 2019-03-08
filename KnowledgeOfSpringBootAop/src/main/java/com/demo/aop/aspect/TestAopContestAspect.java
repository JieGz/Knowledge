package com.demo.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author 揭光智
 * @date 2019/03/07
 */
@Aspect
@Component
public class TestAopContestAspect {

    @Before("execution(* introduce(..))")
    public void testAopContext() {
        System.out.println("I'm test AopContext");
    }
}
