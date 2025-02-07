package com.demo.design.pattern.denpendence.inversion.principle;

/**
 * 依赖倒置原则：面向接口编程,让依赖发生在抽象层。
 * 依赖倒置原则:通过抽象使各个类或模块的实现彼此独立、互不影响，实现模块间的松耦合，让依赖发生在抽象层而不是实现层.
 * 怎么理解 "倒置": 正常思维是:我要开奔驰，所以就依赖奔驰，我要用电脑，我就依赖电脑,都是直接体现在具体的【实现类】中的；但是在编写程序需要对
 * 现实世界进行抽象，抽象的结果就是有了抽象类和接口,然后我们根据系统设计的需求产生了【抽象】间的依赖，代替了正常思维中的事物间的依赖，这就是
 * "倒置"想表达的意思.
 * <p>
 * 结合里氏替换原则得出一个通俗的规则：接口定位public属性和方法，并且声明与其他对象的依赖关系，抽象类负责公共构造部份的逻辑实现，实现类准确
 * 实现业务逻辑，同时在适当的时候对父类进行细化。
 *
 * @author jieguangzhi
 * @date 2025-02-07
 */
public class Client {

    public static void main(String[] args) {

        final Driver driver = new Driver();
        //奔驰
        final Benz benz = new Benz();
        driver.drive(benz);
        //宝马
        final Bmw bmw = new Bmw();
        driver.drive(bmw);
    }
}
