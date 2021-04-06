package com.demo.stream;

import java.util.function.Predicate;

/**
 * @author jieguangzhi
 * @date 2021-03-29
 */
public class PredicateApp {
    public static void main(String[] args) {
        Predicate<Integer> predicate = num -> num > 5;
        System.out.println(predicate.test(6));
        //nagate:取反(非)操作
        System.out.println(predicate.negate().test(4));
        System.out.println();
        Predicate<Integer> rowPredicate = num -> num > 5;
        Predicate<Integer> highPredicate = num -> num < 10;
        //与操作
        System.out.println(rowPredicate.and(highPredicate).test(8));
        System.out.println(rowPredicate.and(highPredicate).test(5));
        System.out.println();
        //或操作
        System.out.println(rowPredicate.or(highPredicate).test(4));
        System.out.println(rowPredicate.or(highPredicate).test(6));
        System.out.println();
        //是否相等操作
        System.out.println(Predicate.isEqual(6).test(6));
        System.out.println(Predicate.isEqual(6).test(5));
    }
}
