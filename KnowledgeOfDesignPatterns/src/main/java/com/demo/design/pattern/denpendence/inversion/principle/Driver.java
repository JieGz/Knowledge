package com.demo.design.pattern.denpendence.inversion.principle;

/**
 * @author jieguangzhi
 * @date 2025-02-07
 */
public class Driver implements IDriver {
    @Override
    public void drive(ICar car) {
        car.run();
    }
}
