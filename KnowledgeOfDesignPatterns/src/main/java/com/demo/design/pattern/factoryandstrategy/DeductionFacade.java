package com.demo.design.pattern.factoryandstrategy;

/**
 * @author 揭光智
 * @date 2019/02/17
 */
public class DeductionFacade {

    public static Card deduc(Card card, Trade trade) {
        EnumStrategyMan type = getDeductionType(trade);
        IDeduction deduction = StrategyFactory.getDeduction(type);
        DeductionContext context = new DeductionContext(deduction);
        context.exec(card, trade);
        return card;
    }

    private static EnumStrategyMan getDeductionType(Trade trade) {
        String value = "abc";
        if (value.equals(trade.getTradeNo())) {
            return EnumStrategyMan.SteadyDeduction;
        } else {
            return EnumStrategyMan.FreeDeduction;
        }
    }
}
