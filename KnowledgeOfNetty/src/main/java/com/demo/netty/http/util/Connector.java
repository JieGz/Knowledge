package com.demo.netty.http.util;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 揭光智
 * @date 2020/05/02
 */
public class Connector {
    private static final Map<Channel, String> connectPool = new ConcurrentHashMap<>();

    public static Map<Channel, String> getConnectPool() {
        return connectPool;
    }
}
