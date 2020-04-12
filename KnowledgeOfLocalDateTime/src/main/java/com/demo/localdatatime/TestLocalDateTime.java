package com.demo.localdatatime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author 揭光智
 * @date 2019/03/22
 */
public class TestLocalDateTime {
    public static void main(String[] args) {
        /*LocalDateTime now = LocalDateTime.of(2019, 03, 25, 00, 00, 00);
        System.out.println(now);
        System.out.println(now.minusDays(2).plusHours(5));
        System.out.println(now.minusDays(9).plusHours(6));*/


        //格式化时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String startTime = LocalDateTime.now().format(formatter);
        System.out.println(startTime);

    }
}
