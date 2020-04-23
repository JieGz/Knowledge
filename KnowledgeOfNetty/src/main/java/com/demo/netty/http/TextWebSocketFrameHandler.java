package com.demo.netty.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

/**
 * @author 揭光智
 * @date 2020/04/23
 */
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

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
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //这里能拿到客户端传过来的消息
        System.out.println(msg.text());
        //增加消息的引用计数,并将它写到ChannelGroup中的所有已连接的客户端
        group.writeAndFlush(msg.retain());
    }

}
