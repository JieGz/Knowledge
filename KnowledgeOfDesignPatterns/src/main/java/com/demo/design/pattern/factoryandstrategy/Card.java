package com.demo.design.pattern.factoryandstrategy;

/**
 * IC卡
 *
 * @author 揭光智
 * @date 2019/02/17
 */
public class Card {

    /**
     * IC卡号码
     */
    private String cardNo = "";

    /**
     * IC卡内的固定交易金额
     */
    private int steadyMoney = 0;

    /**
     * IC卡内的自由交易金额
     */
    private int freeMoney = 0;


    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public int getSteadyMoney() {
        return steadyMoney;
    }

    public void setSteadyMoney(int steadyMoney) {
        this.steadyMoney = steadyMoney;
    }

    public int getFreeMoney() {
        return freeMoney;
    }

    public void setFreeMoney(int freeMoney) {
        this.freeMoney = freeMoney;
    }
}
