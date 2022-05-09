package com.demo.stream;

/**
 * @author jieguangzhi
 * @date 2021-01-27 21:22
 */

public class TestClassMate {
    /** 年级 */
    private Integer grade;

    public static Integer count = 1;

    public TestClassMate() {
        System.out.println(count);
        count = 5;
        System.out.println(count);
    }

    static {
        Test2ClassMate.grade = count;
    }
}

