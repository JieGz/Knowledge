package com.demo.recursive;

/**
 * 递归方法
 *
 * @author 揭光智
 * @date 2019/04/05
 */
public class RecursiveMethod {


    public static void main(String[] args) {
        // countDown(5);

        countDownByFor(5);
    }


    private static void countDown(int n) {
        System.out.println("countDown:" + n);
        if (n > 1) {
            countDown(n - 1);
        }
    }

    private static void countDownByFor(int n) {
        for (int i = n; i >= 1; i--) {
            System.out.println("countDown:" + i);
        }
    }
}
