package com.demo.aop.service;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

/**
 * @author 揭光智
 * @date 2019/03/07
 */
@Service
public class AopTestService {


    public void introduce() {
        System.out.println("Dear,Today is 2019-03-07,I'm learning Aop knowledge!");
    }

    public void sayMessage() {
        System.out.println("sayMessage()");
        ((AopTestService) AopContext.currentProxy()).introduce();
    }
}
