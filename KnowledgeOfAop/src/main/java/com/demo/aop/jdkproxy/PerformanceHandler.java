package com.demo.aop.jdkproxy;

import com.demo.aop.PerformanceMonitor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 性能监视横切代码
 *
 * @author 揭光智
 * @date 2019/02/25
 */
public class PerformanceHandler implements InvocationHandler {

    /**
     * 目标类
     */
    private Object target;

    public PerformanceHandler(Object target) {
        this.target = target;
    }

    /**
     * @param proxy  最终生成的代理实例，一般不会用到
     * @param method 被代理的业务类中的某个方法.
     * @param args   方法对应的入参
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //把性能监视的横切逻辑从业务类,转移到了这个代理类中
        PerformanceMonitor.begin(target.getClass().getName() + "." + method.getName());
        //通过反射调用原来的业务
        Object obj = method.invoke(target, args);
        PerformanceMonitor.end();
        return obj;
    }
}
