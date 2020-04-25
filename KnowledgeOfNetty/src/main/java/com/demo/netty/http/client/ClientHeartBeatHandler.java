package com.demo.netty.http.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;

/**
 * @author 揭光智
 * @date 2020/04/25
 */
public class ClientHeartBeatHandler extends ChannelDuplexHandler {
    private static final ByteBuf HEART_BEAT = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("ping".getBytes(CharsetUtil.UTF_8)));

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if (idleStateEvent.state() == IdleState.WRITER_IDLE) {
                System.out.println("已经10秒钟没有出站消息了,发出一个心跳消息");
                ctx.writeAndFlush(HEART_BEAT).addListener((ChannelFutureListener) future -> {
                            if (!future.isSuccess()) {
                                System.out.println("client close");
                                future.channel().close();
                            }
                        }
                );
            }
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //如果运行过程中服务端挂了,执行重连机制
        System.out.println("channelInactive");
        ConnectUtil.reconnect(ctx.channel());
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exceptionCaught" + cause.getMessage());
        ctx.channel().close();
    }
}
