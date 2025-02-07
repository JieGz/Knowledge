package com.demo.localdatatime.mul;

import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jieguangzhi
 * @date 2023-09-22
 */
public class TestAt {

    private static ConcurrentHashMap<String, AtomicInteger> PENDING_LOCKS = new ConcurrentHashMap<>();
    private static final ExecutorService executorService =
            new ThreadPoolExecutor(100, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
                    new SynchronousQueue<>(),
                    new SyncThreadFactory("task-reader-writer"));

    public static void main(String[] args) {
        final Properties properties = new Properties();
        properties.put("name", "luke");
        properties.put("name", "jieguangzhi");
        System.out.println(properties.get("name"));
    }


}
