package com.demo.netty.http.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.util.CharsetUtil;

/**
 * @author 揭光智
 * @date 2020/04/23
 */
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<Object> {

    private final ChannelGroup group;

    public TextWebSocketFrameHandler(ChannelGroup group) {
        this.group = group;
    }

    /**
     * 重写userEventTriggered方法,处理自定义事件
     *
     * @param ctx ChannelHandlerContext
     * @param evt 消息
     * @throws Exception 异常
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        //如果该事件表示握手成功,则从ChannelPipeline中移除HttpRequestHandler,因为在也不会收到Http消息了
        if (evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
            //移除HttpRequestHandler
            ctx.pipeline().remove(HttpRequestHandler.class);
            //通知所有已经连接的WebSocket客户端,新的客户端已经连接上了
            group.writeAndFlush(new TextWebSocketFrame("Client" + ctx.channel() + " joined"));
            //将新的WebSocket Channel添加到ChannelGroup中,以便它可以接收所有的消息
            group.add(ctx.channel());
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("66666666666666666666666666666666");

        if (msg instanceof TextWebSocketFrame) {
            TextWebSocketFrame text = (TextWebSocketFrame) msg;
            //这里能拿到客户端传过来的消息
            System.out.println(text.text());
            //增加消息的引用计数,并将它写到ChannelGroup中的所有已连接的客户端
            group.writeAndFlush(text.retain());
        } else if (msg instanceof PingWebSocketFrame) {
            PingWebSocketFrame ping = (PingWebSocketFrame) msg;
            System.out.println(ping.content().toString(CharsetUtil.UTF_8));
        }

    }

}
