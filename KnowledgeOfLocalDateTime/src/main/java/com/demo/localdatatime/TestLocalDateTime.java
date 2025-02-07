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
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 揭光智
 * @date 2019/03/22
 */
public class TestLocalDateTime {
    /** 不会出错的格式化 */
    private static DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().appendValue(ChronoField.YEAR) //年
            .appendLiteral("-")
            .appendValue(ChronoField.MONTH_OF_YEAR) //月
            .appendLiteral("-")
            .appendValue(ChronoField.DAY_OF_MONTH) //日
            .appendLiteral(" ")
            .appendValue(ChronoField.HOUR_OF_DAY) //时
            .appendLiteral(":")
            .appendValue(ChronoField.MINUTE_OF_HOUR) //分
            .appendLiteral(":")
            .appendValue(ChronoField.SECOND_OF_MINUTE) //秒
            .appendLiteral(".")
            .appendValue(ChronoField.MILLI_OF_SECOND) //毫秒
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
            .toFormatter();

    private static DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        /*LocalDateTime now = LocalDateTime.of(2019, 03, 25, 00, 00, 00);
        System.out.println(now);
        System.out.println(now.minusDays(2).plusHours(5));
        System.out.println(now.minusDays(9).plusHours(6));*/


        //格式化时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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
        System.out.println("------周");
        System.out.println(LocalDateTime.now().minusWeeks(2).with(DayOfWeek.MONDAY).with(LocalTime.MIN));
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
        System.out.println(LocalDateTime.now().plusDays(7 - LocalDateTime.now().get(WeekFields.ISO.dayOfWeek())).with(DayOfWeek.MONDAY).with(LocalTime.MIN));

        System.out.println("-------月");
        //月
        System.out.println(LocalDateTime.now());
        System.out.println(LocalDateTime.now().getDayOfMonth());
        //本月的起时时间
        System.out.println(LocalDateTime.now().minusMonths(1).with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN));
        //本月的第一个周一
        System.out.println(LocalDateTime.now().with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)).with(LocalTime.MIN));
        LocalDateTime juneOne = LocalDateTime.of(2021, 3, 16, 0, 0, 0, 0);
        System.out.println(juneOne.minusDays(juneOne.get(ChronoField.DAY_OF_MONTH)).with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN));
        System.out.println(juneOne.minusDays(juneOne.get(ChronoField.DAY_OF_MONTH)).with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN).getMonth());
        System.out.println(juneOne.minusDays(juneOne.get(ChronoField.DAY_OF_MONTH)).with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN).getMonth().length(Year.isLeap(juneOne.getYear())));

        System.out.println("----------季度");
        System.out.println(LocalDateTime.now().getMonth().firstMonthOfQuarter());
        Month firstMonthOfLastQuarter = LocalDateTime.now().getMonth().firstMonthOfQuarter().minus(3L);
        Month sixMonthOfLastQuarter = LocalDateTime.now().getMonth().firstMonthOfQuarter().minus(2 * 3L);
        System.out.println(LocalDateTime.now().withMonth(firstMonthOfLastQuarter.getValue()).with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN));
        System.out.println(LocalDateTime.now().withMonth(sixMonthOfLastQuarter.getValue()).with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN));

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

        System.out.println("---------时间计算");
        List<LocalDateTime> times = new ArrayList<>(60);
        LocalDateTime dateTime = LocalDateTime.of(2021, 6, 25, 0, 0, 0);
        LocalDateTime endTime = dateTime.plusHours(1L);
        while (!dateTime.isEqual(endTime)) {
            times.add(dateTime);
            dateTime = dateTime.plusMinutes(1L);
        }

        System.out.println(times.size());
        times.forEach(System.out::println);

        LocalDateTime time = LocalDateTime.of(2021, 8, 8, 0, 0, 0);
        DayOfWeek week = time.getDayOfWeek();
        System.out.println(week.getValue());
        int dayOfMonth = time.getDayOfMonth();
        System.out.println(dayOfMonth);

        System.out.println(LocalDateTime.now().format(dateTimeFormatter));

        formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime dataTime = LocalDateTime.of(LocalDate.parse("20220814", formatter), LocalTime.MIN);
        System.out.println(dataTime);
        final LocalDateTime minusDays = LocalDateTime.now().minusDays(2L).with(LocalTime.MIN);
        System.out.println(minusDays);
        System.out.println(minusDays.isBefore(dataTime) || minusDays.isEqual(dataTime));
        System.out.println("---------------------------");

        formatter = DateTimeFormatter.ofPattern("yyyyMMddHH");
        dataTime = LocalDateTime.parse("2022081610", formatter);

        System.out.println(dataTime);
        LocalDateTime minusHours = LocalDateTime.now().minusHours(2L);
        minusHours = LocalDateTime.parse(minusHours.format(formatter), formatter);
        System.out.println(minusHours);
        System.out.println(minusHours.isBefore(dataTime) || minusHours.isEqual(dataTime));

        System.out.println("----------------------");
        formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        System.out.println(formatter.format(LocalDateTime.now().withSecond(0)));
        System.out.println(LocalDateTime.now().withSecond(0).withNano(0));
        System.out.println(LocalDateTime.parse(LocalDateTime.now().withSecond(0).format(formatter), formatter));
        final LocalDateTime time1 = LocalDateTime.parse(LocalDateTime.now().withSecond(0).format(formatter), formatter);
        final LocalDateTime time2 = LocalDateTime.now().withSecond(0).withNano(0);
        System.out.println(time1.isEqual(time2));

        System.out.println("--------------------");
        System.out.println(localDateTime.with(LocalTime.MAX));
        System.out.println(localDateTime.with(LocalTime.MAX).withMinute(0).withSecond(0).withNano(0));
        System.out.println(localDateTime.withMinute(59).withSecond(0).withNano(0));
        System.out.println(localDateTime.plusDays(7 - time.get(WeekFields.ISO.dayOfWeek())).with(DayOfWeek.MONDAY).with(LocalTime.MIN));
        System.out.println(localDateTime.with(DayOfWeek.SUNDAY).with(LocalTime.MIN));
        System.out.println(localDateTime.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MIN));
        System.out.println(localDateTime.with(TemporalAdjusters.lastDayOfYear()).with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN));
        System.out.println(localDateTime.withMonth(time.getMonth().firstMonthOfQuarter().plus(2L).getValue()).with(TemporalAdjusters.firstDayOfMonth())
                .with(LocalTime.MIN));


        final LocalDateTime value = LocalDateTime.of(2023, 9, 12, 21, 59, 20);
        if (!value.isAfter(LocalDateTime.now().minus(30, ChronoUnit.SECONDS))) {
            System.out.println("1111111111");
        } else {
            System.out.println("22222222222");
        }


        System.out.println("--------20240820------");
        System.out.println(LocalDateTime.now().withMinute(0).format(DateTimeFormatter.ofPattern("yyyyMMddHHmm")));

        System.out.println("---------------");
        final LocalDateTime endTimeA = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);
        LocalDateTime startTimeA = endTimeA.plusHours(-2);
        List<String> partitionTime = new ArrayList<>();
        while (!startTimeA.isAfter(endTimeA)) {
            partitionTime.add(startTimeA.format(DateTimeFormatter.ofPattern("yyyyMMddHHmm")));
            startTimeA = startTimeA.plusHours(1);
        }
        System.out.println(partitionTime);

        System.out.println("----------------");

        // 定义两个LocalDateTime对象
        LocalDateTime startTimeB = LocalDateTime.of(2024, 8, 19, 23, 0);
        LocalDateTime endTimeB = LocalDateTime.of(2024, 8, 20, 0, 3);

        // 计算两个LocalDateTime之间的Duration
        Duration durationB = Duration.between(startTimeB, endTimeB);

        // 获取相差的总分钟数
        long minutes = durationB.toMinutes();
        System.out.println("相关的minutes:" + minutes);

        final LocalDateTime time3 = LocalDateTime.parse("202410211559", DateTimeFormatter.ofPattern("yyyyMMddHHmm")).withSecond(0).withNano(0);
        System.out.println("hive的分区时间:" + time3);
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
