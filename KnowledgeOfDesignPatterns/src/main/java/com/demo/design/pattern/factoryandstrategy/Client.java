package com.demo.design.pattern.factoryandstrategy;

/**
 * @author 揭光智
 * @date 2019/02/17
 */
class Client {

    public static void main(String[] args) {
        Card card = initIC();
        System.out.println("=============IC卡的初始信息=============");
        showCardInfo(card);
        Trade trade = createTrade("No.123");
        System.out.println("\n=============IC卡的消费后的信息=============");
        card = DeductionFacade.deduc(card, trade);
        showCardInfo(card);

        Trade trade1 = createTrade("abc");
        Card card1 = DeductionFacade.deduc(card, trade1);
        System.out.println("\n=============IC卡的消费后的信息=============");
        showCardInfo(card);


    }

    private static Card initIC() {
        Card card = new Card();
        card.setCardNo("No.001");
        card.setSteadyMoney(100);
        card.setFreeMoney(100);
        return card;
    }

    private static Trade createTrade(String tradeNo) {
        Trade trade = new Trade();
        trade.setTradeNo(tradeNo);
        trade.setAmount(50);
        return trade;
    }

    private static void showCardInfo(Card card) {
        System.out.println("IC卡的编号：" + card.getCardNo());
        System.out.println("IC卡的固定金额：" + card.getSteadyMoney());
        System.out.println("IC卡的自由金额：" + card.getFreeMoney());
        System.out.println("==============Card的信息===================");
    }
}
