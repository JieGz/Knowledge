package com.demo.design.pattern.factoryandstrategy;

/**
 * 自由扣款策略：交易的全部信息都由自定金额扣除
 *
 * @author 揭光智
 * @date 2019/02/17
 */
public class FreeDeduction implements IDeduction {

    @Override
    public boolean exec(Card card, Trade trade) {
        card.setFreeMoney(card.getFreeMoney() - trade.getAmount());
        return true;
    }
}
