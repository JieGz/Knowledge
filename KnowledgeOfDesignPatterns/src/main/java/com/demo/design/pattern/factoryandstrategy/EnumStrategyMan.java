package com.demo.design.pattern.factoryandstrategy;

/**
 * @author 揭光智
 * @date 2019/02/17
 */
public enum EnumStrategyMan {

    /**
     * 策略类型
     */
    SteadyDeduction("com.demo.design.pattern.factoryandstrategy.SteadyDeduction"),
    FreeDeduction("com.demo.design.pattern.factoryandstrategy.FreeDeduction");

    String value = "";

    EnumStrategyMan(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
