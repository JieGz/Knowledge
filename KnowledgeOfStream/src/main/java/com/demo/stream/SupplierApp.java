package com.demo.stream;

import java.util.function.Supplier;

/**
 * @author jieguangzhi
 * @date 2021-03-31
 */
public class SupplierApp {
    public static void main(String[] args) {
        Supplier<String> logSupplier = () -> "I'm log";
        System.out.println(logSupplier.get());
    }
}
