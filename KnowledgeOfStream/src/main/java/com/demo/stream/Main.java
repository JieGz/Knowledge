package com.demo.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 揭光智
 * @date 2019/02/07
 */
public class Main {

    public static void main(String[] args) {
        learnStream();
        reduce3th();

        collectEle();

        groupBY();
    }


    private static void learnStream() {
        List<Integer> lists = new ArrayList<>();
        lists.add(1);
        lists.add(2);
        lists.add(3);
        lists.add(4);
        lists.add(5);
        lists.add(6);

        Optional<Integer> sum = lists.parallelStream().reduce((a, b) -> a + b);
        //21
        sum.ifPresent(integer -> System.out.println("list的总和为:" + integer));
        /*<====> lists.stream().reduce((a, b) -> a + b).ifPresent(System.out::println);*/


        Integer sum2 = lists.stream().reduce(0, (a, b) -> a + b);
        //21
        System.out.println("list的总和为:" + sum2);

        Optional<Integer> product = lists.stream().reduce((a, b) -> a * b);
        //720
        product.ifPresent(integer -> System.out.println("list的积为:" + integer));

        Integer product2 = lists.parallelStream().reduce(1, (a, b) -> a * b);
        //720
        System.out.println("list的积为:" + product2);
    }


    private static void reduce3th() {
        List<Integer> lists = new ArrayList<>();
        lists.add(1);
        lists.add(2);
        lists.add(3);

        Integer product = lists.parallelStream().reduce(1, (a, b) -> a * b * 2,
                (a, b) -> a * b * 2);
        //48
        System.out.println("reduce3th:" + product);
    }

    /**
     * 将多个元素,收集成一个{@link List}
     */
    private static void collectEle() {
        List<String> list = Stream.of("a", "b", "c").collect(Collectors.toList());
        System.out.println(list);
    }


    private static void groupBY() {
        Set<ClassMate> mates = Stream.of(ClassMate.builder().grade("三级年二班").name("小明").sex("男").build(),
                ClassMate.builder().grade("四级年三班").name("小强").sex("男").build(),
                ClassMate.builder().grade("三级年二班").name("小美").sex("女").build(),
                ClassMate.builder().grade("四级年三班").name("小花").sex("女").build()).collect(Collectors.toSet());
        Map<String, List<ClassMate>> map = mates.stream().collect(Collectors.groupingBy(ClassMate::grade));
        System.out.println(map);
    }
}
