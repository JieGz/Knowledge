package com.demo.aop.web;

import com.demo.aop.service.AopTestService;
import com.demo.aop.waiter.NaiveWaiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 揭光智
 * @date 2019/02/27
 */
@RestController
public class AopController {

    @Autowired
    private NaiveWaiter naiveWaiter;

    @Autowired
    private AopTestService aopTestService;

    @GetMapping("test")
    public String test() {
        naiveWaiter.greetTo("光智");
        return "succeed";
    }


    @GetMapping("test-aop-context")
    public String testContext() {
        aopTestService.sayMessage();
        return "succeed";
    }
}
