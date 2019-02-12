package com.demo.netty.decoder;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author 揭光智
 * @date 2019/02/12
 */
public class TimeServerChildChannelHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
        ch.pipeline().addLast(new StringDecoder());
        ch.pipeline().addLast(new TimeServerHandler());
    }
}
