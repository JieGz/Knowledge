package com.demo;


import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author jieguangzhi
 * @date 2021-07-19
 */
public class App {


    public static void main(String[] args) {
        List<Long> tagIdList = Lists.newArrayList(1L, 3L, 5L);
        String join = Joiner.on(",").join(tagIdList);
        System.out.println("join:" + join);
    }
}
