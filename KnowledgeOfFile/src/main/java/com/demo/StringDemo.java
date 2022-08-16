package com.demo;

/**
 * @author jieguangzhi
 * @date 2022-08-16
 */
public class StringDemo {

    public static void main(String[] args) {
        String a = "/a/b/c";
        String b = "/a/b/c";
        final int i = a.compareTo(b);
        System.out.println(i);
    }
}
