package com.demo;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;

/**
 * @author jieguangzhi
 * @date 2022-08-16
 */
public class DisruptorDemo {

    public static void main(String[] args) {
        //1. 实例化一个disruptor对象
        final OrderEventFactory factory = new OrderEventFactory();
        int ringBufferSize = 1024;
        final OrderEventThreadFactory threadFactory = new OrderEventThreadFactory();
        final Disruptor<OrderEvent> disruptor = new Disruptor<>(factory, ringBufferSize, threadFactory, ProducerType.SINGLE, new BlockingWaitStrategy());
        //2. 添加消费者的监听
        disruptor.handleEventsWith(new OrderEventHandler());
        //3. 启动disruptor
        disruptor.start();
        //4. 获取实际存储数据的容器: RingBuffer
        final RingBuffer<OrderEvent> ringBuffer = disruptor.getRingBuffer();
        //5. 发送消息
        final OrderEventProducer producer = new OrderEventProducer(ringBuffer);

        final ByteBuffer buffer = ByteBuffer.allocate(8);
        int size = 100;
        for (int i = 0; i < size; i++) {
            buffer.putLong(0, i);
            producer.sendData(buffer);
        }

        //关闭disruptor
        disruptor.shutdown();
    }
}
