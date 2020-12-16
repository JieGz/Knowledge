package com.demo;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class StopwatchDemo {

    public static void main(String[] args) throws Exception {
        Stopwatch started = Stopwatch.createStarted();
        TimeUnit.SECONDS.sleep(2);
        long elapsed = started.elapsed(TimeUnit.SECONDS);
        System.out.println(elapsed);
        TimeUnit.SECONDS.sleep(5);
        long elapsed1 = started.elapsed(TimeUnit.SECONDS);
        System.out.println(elapsed1);
        Stopwatch stop = started.stop();
        TimeUnit.SECONDS.sleep(2);
        long elapsed2 = stop.elapsed(TimeUnit.SECONDS);

        System.out.println(elapsed2);
    }
}
