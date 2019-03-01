package com.demo.aop.waiter;

import org.springframework.stereotype.Component;

/**
 * @author 揭光智
 * @date 2019/02/27
 */
@Component
public class NaiveWaiter implements Waiter {

    @Override
    public void greetTo(String clientName) {
        System.out.println("NaiveWaiter服务员: 问候" + clientName);
    }

    @Override
    public void serveTo(String clientName) {
        System.out.println("NaiveWaiter服务员：为" + clientName + "提供服务");
    }
}
