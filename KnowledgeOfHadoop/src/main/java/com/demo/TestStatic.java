package com.demo;

/**
 * @author jieguangzhi
 * @date 2021-07-28
 */
public class TestStatic {

    static {
        System.out.println("静态态码快!");
    }

    {
        System.out.println("构造代码块");
    }

    public TestStatic() {
        System.out.println("构造函数");
    }


    public static void main(String[] args) {
        new TestStatic();
    }
}
