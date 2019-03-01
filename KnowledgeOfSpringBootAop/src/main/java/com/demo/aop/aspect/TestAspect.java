package com.demo.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
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

    /**
     * 目标类连接点对象,必须是增强方法的第一个参数.
     * @param pjp
     * @throws Throwable
     */
    @Around("execution(* greetTo(..)) && target(com.demo.aop.waiter.NaiveWaiter)")
    public void joinPointAccess(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("============joinPointAccess==============");
        //获取连接点方法运行时的时的入参列表
        System.out.println("args[0]:" + pjp.getArgs()[0]);
        //获取连接点所在的目标类对象
        System.out.println("target object" + pjp.getTarget().getClass());
        //通过反射调用目标类连接点方法
        pjp.proceed();

        System.out.println("============joinPointAccess==============");
    }
}
