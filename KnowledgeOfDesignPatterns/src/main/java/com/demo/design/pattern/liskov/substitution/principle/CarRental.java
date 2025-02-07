package com.demo.design.pattern.liskov.substitution.principle;

/**
 * @author jieguangzhi
 * @date 2025-02-07
 */
public class CarRental {
    public void rentCar(Car car) {
        car.drive();
    }
}
