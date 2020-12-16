package com.demo.thread;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 揭光智
 * @date 2020/04/18
 */
@Slf4j
public class SampleTest {

    public static void main(String[] args) {
        Map<Integer, List<Integer>> buildTeamMap = Maps.newConcurrentMap();

        buildTeamMap.put(1, Lists.newArrayList(1, 2, 3));
        List<Integer> list = buildTeamMap.get(1);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }
}