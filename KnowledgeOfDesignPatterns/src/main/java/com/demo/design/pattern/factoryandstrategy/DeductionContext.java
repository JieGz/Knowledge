package com.demo.design.pattern.factoryandstrategy;

/**
 * @author 揭光智
 * @date 2019/02/17
 */
public class DeductionContext {
    private IDeduction deduction;

    public DeductionContext(IDeduction deduction) {
        this.deduction = deduction;
    }

    public boolean exec(Card card, Trade trade) {
        return deduction.exec(card, trade);
    }
}
