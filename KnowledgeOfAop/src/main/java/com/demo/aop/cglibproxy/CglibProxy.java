package com.demo.aop.cglibproxy;

import com.demo.aop.PerformanceMonitor;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 缺点,业务类不能是final的,业务方法不能是private修饰的
 *
 * @author 揭光智
 * @date 2019/02/25
 */
public class CglibProxy implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz) {
        //设置被代理的业务类,创建业务类的子类
        enhancer.setSuperclass(clazz);
        //设置织入横切逻辑
        enhancer.setCallback(this);
        //通过字节码技术,动态创建业务类子类的实例
        return enhancer.create();
    }


    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        PerformanceMonitor.begin(obj.getClass().getName() + "." + method.getName());
        //通过代理类(业务类的子类)，调用业务类中的方法
        Object result = proxy.invokeSuper(obj, args);
        PerformanceMonitor.end();
        return result;
    }
}
