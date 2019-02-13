package com.demo.design.pattern.strategy;

/**
 * @author 揭光智
 * @date 2019/02/13
 */
public class Context {
    private IStrategy strategy;

    public Context(IStrategy strategy) {
        this.strategy = strategy;
    }

    public void operate() {
        this.strategy.operate();
    }
}
