package com.demo;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author jieguangzhi
 * @date 2022-08-16
 */
public class OrderEventProducer {
    private RingBuffer<OrderEvent> ringBuffer;

    public OrderEventProducer(RingBuffer<OrderEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void sendData(ByteBuffer data) {
        //1. 在生产者发送消息的时候,首先需要从我们的ringBuffer里面获取一个可用的序号
        final long sequence = ringBuffer.next();
        try {
            //2. 根据序号,找到具体的"OrderEvent"事件元素, 注意: 此时获取到的OrderEvent对象是一个还没有被赋值的对象
            final OrderEvent orderEvent = ringBuffer.get(sequence);
            //3. 进行实际的赋值处理
            orderEvent.setPrice(data.getLong(0));
        } finally {
            //4. 提交发布操作
            ringBuffer.publish(sequence);
        }
    }
}
