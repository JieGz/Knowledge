package com.demo.netty.http.server;

import com.demo.netty.http.constance.Constance;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author 揭光智
 * @date 2020/04/22
 */
public class LuckServerInitializer extends ChannelInitializer<Channel> {
    private final ChannelGroup group;

    public LuckServerInitializer(ChannelGroup group) {
        this.group = group;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //空闲链路检测机制
        pipeline.addLast(new IdleStateHandler(Constance.READER_IDLE_TIME, Constance.WRITER_IDLE_TIME, Constance.ALL_IDLE_TIME, TimeUnit.SECONDS));
        //自定义心跳机制ping-ping型心跳或者是ping-pong型心跳
        pipeline.addLast(new HeartBeatHandler());
        pipeline.addLast(new HttpServerCodec());
        //主要支持异步的流码(大文件传输)
        pipeline.addLast(new ChunkedWriteHandler());
        //提供一个聚合的HttpRequest.因为如果一个Http携带的数据过大,会分开几次传输
        pipeline.addLast(new HttpObjectAggregator(64 * 1024));
        pipeline.addLast(new HttpRequestHandler("/ws"));
        //处理协议升级握手,以及3种控制帧-Close,Ping和Pong; Text Binary数据帧将会传递到下一个(由你实现的)ChannelHandler进行处理
        //如果求请的端点是/ws,则处理升级握手事宜
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        pipeline.addLast(new TextWebSocketFrameHandler(group));
    }
}
