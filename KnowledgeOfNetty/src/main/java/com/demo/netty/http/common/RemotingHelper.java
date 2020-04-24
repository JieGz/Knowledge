package com.demo.netty.http.common;


import io.netty.channel.Channel;

import java.net.SocketAddress;

/**
 * @author 揭光智
 * @date 2020/04/24
 */
public class RemotingHelper {

    /**
     * 解析出channel对应的ip
     *
     * @param channel Channel
     * @return ip address
     */
    public static String parseChannelRemoteAddr(final Channel channel) {
        if (null == channel) {
            return "";
        }
        SocketAddress remote = channel.remoteAddress();
        final String addr = remote != null ? remote.toString() : "";
        if (addr.length() > 0) {
            int index = addr.lastIndexOf("/");
            if (index >= 0) {
                return addr.substring(index + 1);
            }
        }
        return "";
    }
}
