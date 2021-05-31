package com.demo.controller;

import com.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/test")
    public String test() {
        return this.getData("Luke");
    }

    private String getData(String name) {
        return this.testService.test(name);
    }
}
