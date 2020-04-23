package com.demo.netty.http;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

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
