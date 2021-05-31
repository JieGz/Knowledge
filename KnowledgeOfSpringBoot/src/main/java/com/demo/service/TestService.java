package com.demo.service;

import org.springframework.stereotype.Service;

@Service
public class TestService {

    public String test(String name) {
        return "TestService:" + name;
    }
}
