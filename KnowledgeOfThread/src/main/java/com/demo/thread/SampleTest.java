package com.demo.thread;

/**
 * @author 揭光智
 * @date 2020/04/18
 */
public class SampleTest {

    public static void main(String[] args) {
        int i = 0;

        while (true) {
            if (i == 50) {
                i += 50;
                System.out.println(i);
                continue;
            }
            System.out.println(i);
            i++;
        }


    }
}
