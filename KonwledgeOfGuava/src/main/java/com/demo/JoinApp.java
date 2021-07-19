package com.demo;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.StringJoiner;

/**
 * @author jieguangzhi
 * @date 2021-07-12
 */
public class JoinApp {

    public static void main(String[] args) {
        List<String> lists = Lists.newArrayList("a,b", "c,d", "e,f,g");

        StringJoiner joiner = new StringJoiner(",");
        for (String s : lists) {
            joiner.add(s);
        }
        System.out.println(joiner.toString());
    }
}
