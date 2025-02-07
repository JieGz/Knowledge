package com.demo.design.pattern.liskov.substitution.principle;

/**
 * @author jieguangzhi
 * @date 2025-02-07
 */
public class GasolineCar extends Car {
    public void drive() {
        System.out.println("油车正在行驶，需要加汽油。");
    }
}
