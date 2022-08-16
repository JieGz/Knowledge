package com.demo;

import java.util.regex.Pattern;

/**
 * @author jieguangzhi
 * @date 2021-02-04 11:04
 */
public class Demo {

    private final static Pattern PATTERN = Pattern.compile("20[2-9][0-9](0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[0-1])");

    public static void main(String[] args) {
        //String content = "20300816";
        String content = "20300816";

        //20200101~20991231
        //String regex = "^20[2-9][0-9](0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[0-1])$";

        //00-23
        //String regex = "^[01][0-9]|2[0-3]$";
        //00-59
        //String regex = "^[0-5][0-9]\\.(log|txt)$";

        String regex = "^20[2-9][0-9](0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[0-1])$|^[01][0-9]|2[0-3]$|^[0-5][0-9]\\.(log|txt)$";

        final boolean matches = Pattern.matches(regex, content);
        System.out.println(matches);


    }
}
