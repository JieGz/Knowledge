package com.demo.netty.http.server;

import com.demo.netty.http.common.RemotingHelper;
import com.demo.netty.http.constance.Constance;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;

/**
 * 如果你的ChannelHandler需要拦截操作或者状态更新,那么这是一个很好的起点{@link ChannelDuplexHandler}
 *
 * @author 揭光智
 * @date 2020/04/24
 */
public class HeartBeatHandler extends ChannelDuplexHandler {
    private static final ByteBuf HEART_BEAT = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("ping".getBytes(CharsetUtil.UTF_8)));

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                System.out.println(Constance.READER_IDLE_TIME + "秒没有入站消息了:=========>可能已经断开链接了" + RemotingHelper.parseChannelRemoteAddr(ctx.channel()));
            } else if (event.state() == IdleState.WRITER_IDLE) {
                System.out.println(Constance.WRITER_IDLE_TIME + "秒没有出站消息了,发送一个心跳" + RemotingHelper.parseChannelRemoteAddr(ctx.channel()));
                ctx.writeAndFlush(HEART_BEAT);
            } else if (event.state() == IdleState.ALL_IDLE) {
                System.out.println(Constance.ALL_IDLE_TIME + "秒没有出-入站消息了,============>基本铁定已经断开连接了" + RemotingHelper.parseChannelRemoteAddr(ctx.channel()));
            }
        } else {//important 如果这里明确他是一个空闲事件了,就没有必要再把事件往后面传递了,除非后面还有地方要处理这个事件
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("channelInactive:" + RemotingHelper.parseChannelRemoteAddr(ctx.channel()));
    }
}
