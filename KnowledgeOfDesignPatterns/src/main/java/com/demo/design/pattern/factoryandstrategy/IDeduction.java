package com.demo.design.pattern.factoryandstrategy;

/**
 * 扣款策略接口
 *
 * @author 揭光智
 * @date 2019/02/17
 */
public interface IDeduction {

    /**
     * 扣款，通过提供的IC卡信息和交易信息来扣款
     *
     * @param card  IC卡信息
     * @param trade 交易信息
     * @return 是否扣款成功
     */
    boolean exec(Card card, Trade trade);
}
