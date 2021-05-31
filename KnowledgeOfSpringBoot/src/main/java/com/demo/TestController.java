package com.demo;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jieguangzhi
 * @date 2021-05-10
 */
@RestController
public class TestController {

    @PostMapping("/test")
    public String test(@Validated @RequestBody TestRequest request) {
        System.out.println(request.getAggregateEnum().getType());
        return "Hello";
    }
}
