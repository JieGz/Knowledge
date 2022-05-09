package com.demo;

import java.util.LinkedList;

public class UseTest {
    public static void main(String[] args) {
        LinkedList<Long> list = new LinkedList<>();
        list.add(5L);
        list.addFirst(4L);
        list.addLast(6L);
        System.out.println(list);
    }
}
