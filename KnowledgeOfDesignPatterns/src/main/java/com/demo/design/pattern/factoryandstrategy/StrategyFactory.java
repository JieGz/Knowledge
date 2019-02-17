package com.demo.design.pattern.factoryandstrategy;

/**
 * @author 揭光智
 * @date 2019/02/17
 */
public class StrategyFactory {

    public static IDeduction getDeduction(EnumStrategyMan strategy) {
        IDeduction deduction = null;
        try {
            deduction = (IDeduction) Class.forName(strategy.value).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return deduction;
    }
}
