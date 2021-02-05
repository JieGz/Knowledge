package com.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jieguangzhi
 * @date 2021-02-04 11:04
 */
public class Demo {
    private static final Pattern pattern = Pattern.compile("\\d+");

    public static void main(String[] args) {
        String line = "aaa2233bbb";
        Matcher matcher = pattern.matcher(line);
        //String group = matcher.group();
        //System.out.println(group);

        String regEx = "[a-z]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(line);
        System.out.println(m.replaceAll("").trim());
    }
}
