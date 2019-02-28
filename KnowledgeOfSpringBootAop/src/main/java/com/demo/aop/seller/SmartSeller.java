package com.demo.aop.seller;

/**
 * @author 揭光智
 * @date 2019/02/28
 */
public class SmartSeller implements Seller {

    @Override
    public void sell(String goods, String clientName) {
        System.out.println("SmartSeller : sell" + goods + " to" + clientName);
    }
}
