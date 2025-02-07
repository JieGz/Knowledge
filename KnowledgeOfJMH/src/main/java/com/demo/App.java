package com.demo;

import cn.hutool.core.date.LocalDateTimeUtil;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 5, timeUnit = TimeUnit.SECONDS)
@Threads(8)
@Fork(2)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class App {

    /**
     * localDateTime Formatter.
     */
    public interface Fmt {

        /**
         * 日期小时分钟格式化.
         */
        DateTimeFormatter DATE_HOUR_MINUTE_FMT = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

        /**
         * 小时分钟格式化.
         */
        DateTimeFormatter HOUR_MINUTE_FMT = DateTimeFormatter.ofPattern("HHmm");

    }

    @Benchmark
    public void testStringAdd() {
        final String format = LocalDateTimeUtil.of(System.currentTimeMillis()).format(Fmt.DATE_HOUR_MINUTE_FMT);
        final String s = format.substring(8, 10);
        //System.out.println(s);
    }

    @Benchmark
    public void testStringBuildAdd() {
        long timestamp = System.currentTimeMillis(); // 获取当前时间的时间戳
        Instant instant = Instant.ofEpochMilli(timestamp);
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        int hour = dateTime.getHour();
        //System.out.println("Hour: " + hour);
    }

    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(App.class.getSimpleName())
                //.output("/Users/luke/Downloads/benchmark.log")
                .build();

        new Runner(options).run();
    }
}
