package com.demo.design.pattern.liskov.substitution.principle;

/**
 * 里氏替换原则: 子类对象必须能够替换父类对象，换句话说就是：所有接收父类参数的方法，都可以使用子类传参进行调用。
 *
 * @author jieguangzhi
 * @date 2025-02-07
 */
public class Client {

    public static void main(String[] args) {
        //里氏替换原则的核心体现在CarRental类中的rentCar方法接收的参数是抽象的类Car,因此传油车和电车子类到这个方法进行调用都可以正确使用.
        final CarRental carRental = new CarRental();
        //油车
        final GasolineCar gasolineCar = new GasolineCar();
        carRental.rentCar(gasolineCar);
        //电车
        final ElectricCar electricCar = new ElectricCar();
        carRental.rentCar(electricCar);
    }
}
