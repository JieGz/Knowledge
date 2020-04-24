package com.demo.netty.http.common;

import io.netty.channel.Channel;

/**
 * @author 揭光智
 * @date 2020/04/24
 */
public class RemotingUtil {

    public static void closeChannel(Channel channel) {
        String addrRemote = RemotingHelper.parseChannelRemoteAddr(channel);
        channel.closeFuture().addListener(future -> System.out.println("closeChannel: close the connection to remote address[" + addrRemote + "]" + "result:" + future.isSuccess()));
    }
}
