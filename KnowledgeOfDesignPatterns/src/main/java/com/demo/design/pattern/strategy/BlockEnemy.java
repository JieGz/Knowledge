package com.demo.design.pattern.strategy;

/**
 * @author 揭光智
 * @date 2019/02/12
 */
public class BlockEnemy implements IStrategy {
    @Override
    public void operate() {
        System.out.println("孙夫人断后,挡住追兵");
    }
}
