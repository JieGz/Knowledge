package com.demo.netty;

import io.netty.util.HashedWheelTimer;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * Netty提供的时间轮工具测试
 *
 * @author jieguangzhi
 * @date 2021-05-12
 */
@Slf4j
public class HashWheelTimerTest {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
        log.info(LocalDateTime.now().format(formatter));
        HashedWheelTimer wheelTimer = new HashedWheelTimer(new DefaultThreadFactory("test"), 1, TimeUnit.MILLISECONDS, 1024, false);
        for (int i = 0; i < 100000; i++) {
            wheelTimer.newTimeout(timeout -> log.info("时间到了,该执行任务了"), 5, TimeUnit.SECONDS);
        }
        //2021-05-12 10:50:44 164
        //2021-05-12 10:50:49.505
    }
}
