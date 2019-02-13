package com.demo.design.pattern.strategy;

/**
 * @author 揭光智
 * @date 2019/02/12
 */
public class GivenGreenLight implements IStrategy {
    @Override
    public void operate() {
        System.out.println("求吴国太开绿灯,放行!");
    }
}
