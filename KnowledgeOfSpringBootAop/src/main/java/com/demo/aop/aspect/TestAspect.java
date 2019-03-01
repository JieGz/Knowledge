package com.demo.aop.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author 揭光智
 * @date 2019/02/28
 */
@Aspect
@Component
public class TestAspect {

    @After("@annotation(com.demo.aop.annotation.NeedTest)")
    public void needTestFun() {
        System.out.println("needTestFun() executed!");
    }
}
