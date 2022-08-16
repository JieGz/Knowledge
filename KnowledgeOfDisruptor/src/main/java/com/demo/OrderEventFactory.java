package com.demo;

import com.lmax.disruptor.EventFactory;

/**
 * @author jieguangzhi
 * @date 2022-08-16
 */
public class OrderEventFactory implements EventFactory<OrderEvent> {

    @Override
    public OrderEvent newInstance() {
        return new OrderEvent();
    }
}
