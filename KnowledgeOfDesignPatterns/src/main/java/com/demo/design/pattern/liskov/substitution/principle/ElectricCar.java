package com.demo.design.pattern.liskov.substitution.principle;

/**
 * @author jieguangzhi
 * @date 2025-02-07
 */
public class ElectricCar extends Car {
    @Override
    public void drive() {
        System.out.println("电车正在行驶，需要充电。");
    }
}
