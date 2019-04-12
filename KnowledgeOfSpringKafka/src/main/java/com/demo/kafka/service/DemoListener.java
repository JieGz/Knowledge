package com.demo.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author 揭光智
 * @date 2019/04/12
 */
@Component
public class DemoListener {

    @KafkaListener(id = "demo", topics = "test")
    public void listen(String msgData) {
        System.out.println("接收到的消息====>" + msgData);
    }
}
