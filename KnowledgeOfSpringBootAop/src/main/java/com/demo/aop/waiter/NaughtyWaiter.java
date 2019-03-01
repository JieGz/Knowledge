package com.demo.aop.waiter;

import com.demo.aop.annotation.NeedTest;
import org.springframework.stereotype.Component;

/**
 * @author 揭光智
 * @date 2019/02/28
 */
@Component
public class NaughtyWaiter implements Waiter {

    @NeedTest
    @Override
    public void greetTo(String clientName) {
        System.out.println("NaughtyWaiter服务员: 问候 " + clientName);
    }

    @Override
    public void serveTo(String clientName) {
        System.out.println("NaughtyWaiter：为" + clientName + "提供服务");
    }
}
