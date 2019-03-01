package com.demo.aop.web;

import com.demo.aop.seller.Seller;
import com.demo.aop.waiter.NaiveWaiter;
import com.demo.aop.waiter.NaughtyWaiter;
import com.demo.aop.waiter.Waiter;
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

    @GetMapping("test")
    public String test() {

        naiveWaiter.greetTo("光智");

        return "succeed";
    }
}
