package com.demo.design.pattern.factoryandstrategy;

/**
 * 交易信息
 * @author 揭光智
 * @date 2019/02/17
 */
public class Trade {

    private String tradeNo = "";

    private int amount = 0;

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }
}
