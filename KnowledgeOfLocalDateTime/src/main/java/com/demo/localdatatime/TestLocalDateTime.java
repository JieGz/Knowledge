package com.demo.localdatatime;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.time.temporal.WeekFields;
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

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime beforeOneHours = LocalDateTime.now().minusHours(1L);
        System.out.println(localDateTime + "  " + beforeOneHours);


        //周
        System.out.println("------");
        //当前时间
        System.out.println(LocalDateTime.now());
        //当前时间为这个星期的第几天(周一算起)
        System.out.println(LocalDateTime.now().get(WeekFields.ISO.dayOfWeek()));
        //当前时间为这个星期的第几天(周日算起)
        System.out.println(LocalDateTime.now().get(WeekFields.SUNDAY_START.dayOfWeek()));
        //x天前的当前时间(周一算起)
        System.out.println(LocalDateTime.now().minusDays(LocalDateTime.now().get(WeekFields.ISO.dayOfWeek())));
        //x天前的起始时间(周一算起)
        System.out.println(LocalDateTime.now().minusDays(LocalDateTime.now().get(WeekFields.ISO.dayOfWeek())).with(LocalTime.MIN));
        //x天前的起始时间(周日算起)
        System.out.println(LocalDateTime.now().minusDays(LocalDateTime.now().get(WeekFields.SUNDAY_START.dayOfWeek())).with(LocalTime.MIN));
        //上周的第一天的起始时间(周一算起)
        System.out.println(LocalDateTime.now().minusDays(LocalDateTime.now().get(WeekFields.ISO.dayOfWeek())).with(DayOfWeek.MONDAY).with(LocalTime.MIN));
        //上周的第一天的起始时间(周日算起)
        System.out.println(LocalDateTime.now().minusDays(LocalDateTime.now().get(WeekFields.SUNDAY_START.dayOfWeek())).with(DayOfWeek.MONDAY).with(LocalTime.MIN));

        System.out.println("-------月");
        //月
        System.out.println(LocalDateTime.now());
        System.out.println(LocalDateTime.now().getDayOfMonth());
        //本月的起时时间
        System.out.println(LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN));
        //本月的第一个周一
        System.out.println(LocalDateTime.now().with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)).with(LocalTime.MIN));
        LocalDateTime juneOne = LocalDateTime.of(2021, 3, 16, 0, 0, 0, 0);
        System.out.println(juneOne.minusDays(juneOne.get(ChronoField.DAY_OF_MONTH)).with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN));
        System.out.println(juneOne.minusDays(juneOne.get(ChronoField.DAY_OF_MONTH)).with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN).getMonth());
        System.out.println(juneOne.minusDays(juneOne.get(ChronoField.DAY_OF_MONTH)).with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN).getMonth().length(Year.isLeap(juneOne.getYear())));

        System.out.println("----------季度");
        System.out.println(LocalDateTime.now().getMonth().firstMonthOfQuarter());
        Month firstMonthOfLastQuarter = LocalDateTime.now().getMonth().firstMonthOfQuarter().minus(3L);
        System.out.println(LocalDateTime.now().withMonth(firstMonthOfLastQuarter.getValue()).with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN));

        System.out.println("----------年");
        System.out.println(LocalDateTime.now().with(TemporalAdjusters.firstDayOfYear()).with(LocalTime.MIN));

        System.out.println("------计算");
        LocalDateTime oldTime = LocalDateTime.of(2021, 3, 15, 19, 41, 0);
        LocalDateTime now = LocalDateTime.now();
        System.out.println(oldTime.until(now, unit("1Q")));
        System.out.println(LocalDateTime.now().minus(1, IsoFields.QUARTER_YEARS));

        System.out.println("------时间戳");
        System.out.println(System.currentTimeMillis());
        System.out.println(Instant.now().toEpochMilli());
        System.out.println(Instant.now().getEpochSecond());
    }

    private static TemporalUnit unit(String exp) {
        char site = exp.charAt(exp.length() - 1);
        switch (site) {
            case 'W':
                return ChronoUnit.WEEKS;
            case 'h':
                return ChronoUnit.HOURS;
            case 'D':
                return ChronoUnit.DAYS;
            case 'M':
                return ChronoUnit.MONTHS;
            case 'y':
                return ChronoUnit.YEARS;
            case 'Q':
                return IsoFields.QUARTER_YEARS;
            default:
                return ChronoUnit.MINUTES;
        }
    }
}
