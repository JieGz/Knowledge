package com.demo.kafka.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 揭光智
 * @date 2019/04/12
 */
@RestController
public class KafkaController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping("/test")
    public String sayHello() {
        kafkaTemplate.send("test", "Hello kafka,I am Luke");

        return "hello ,I am spring kafka app";
    }
}
