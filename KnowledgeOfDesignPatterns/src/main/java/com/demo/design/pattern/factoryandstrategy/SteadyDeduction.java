package com.demo.design.pattern.factoryandstrategy;

/**
 * 固定扣款策略:固定金额和自由金额各扣一半
 *
 * @author 揭光智
 * @date 2019/02/17
 */
public class SteadyDeduction implements IDeduction {

    @Override
    public boolean exec(Card card, Trade trade) {
        int halfMoney = (int) Math.rint(trade.getAmount() / 2.0);
        card.setSteadyMoney(card.getSteadyMoney() - halfMoney);
        card.setFreeMoney(card.getFreeMoney() - halfMoney);
        return true;
    }
}
