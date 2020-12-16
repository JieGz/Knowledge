package com.demo.juc.tools;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {

    public static void main(String[] args) {
        //调试HashMap,需要使用一个小技巧,因为JVM在启动的时候,有很多地方用到了HashMap,所有需要用过Resume Program(F9)来跳过那些系统的初始化
        //通过调用链找到自己的初始化的过程,然后再开始调试.
        Map<String, String> map = new HashMap<>(2);
        map.put("a", "b");
    }
}
