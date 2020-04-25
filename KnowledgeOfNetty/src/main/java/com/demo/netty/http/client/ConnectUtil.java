package com.demo.netty.http.client;

import io.netty.channel.Channel;

import java.util.concurrent.TimeUnit;

/**
 * @author 揭光智
 * @date 2020/04/25
 */
public class ConnectUtil {

    private static LukeHttpClient client;

    public static void initClient(LukeHttpClient client) {
        ConnectUtil.client = client;
    }

    private static LukeHttpClient getClient() {
        return client;
    }

    public static void connect(Channel channel) {
        channel.eventLoop().schedule(() -> getClient().start(), 3L, TimeUnit.SECONDS);
    }

    public static void reconnect(Channel channel) {
        connect(channel);
    }
}
