package com.demo.localdatatime;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
       // String startTime = LocalDateTime.now().format(formatter);
        //System.out.println(startTime);
        LocalDateTime st = LocalDateTime.of(LocalDate.parse("2021-03-11"), LocalTime.MIN);
        LocalDateTime et = LocalDateTime.of(LocalDate.parse("2021-03-13"), LocalTime.MIN);
       st = st.plusDays(1);

        Duration duration = Duration.between(st, et);
        long days = duration.toDays();
        System.out.println(days);

        String format = st.format(formatter);
        Object dt = "2021-03-12";
        if (Objects.equals(format, dt)) {
            System.out.println("时间对上了");
        }
    }
}
