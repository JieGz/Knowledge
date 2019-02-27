package com.demo.aop.waiter;

/**
 * @author 揭光智
 * @date 2019/02/27
 */
public interface Waiter {

    /**
     * 问候
     */
    void greetTo(String clientName);

    /**
     * 提供服务
     */
    void serveTo(String clientName);
}
