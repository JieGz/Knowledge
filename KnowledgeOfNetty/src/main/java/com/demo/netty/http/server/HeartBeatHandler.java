package com.demo.netty.http.server;

import com.demo.netty.http.common.RemotingHelper;
import com.demo.netty.http.constance.Constance;
import com.demo.netty.http.util.Connector;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 如果你的ChannelHandler需要拦截操作或者状态更新,那么这是一个很好的起点{@link ChannelDuplexHandler}
 *
 * @author 揭光智
 * @date 2020/04/24
 */
@Slf4j
public class HeartBeatHandler extends ChannelDuplexHandler {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                System.out.println(Constance.READER_IDLE_TIME + "秒没有入站消息了:=========>可能已经断开链接了" + RemotingHelper.parseChannelRemoteAddr(ctx.channel()));
            } else if (event.state() == IdleState.WRITER_IDLE) {
                System.out.println(Constance.WRITER_IDLE_TIME + "秒没有出站消息了,发送一个心跳" + RemotingHelper.parseChannelRemoteAddr(ctx.channel()));
                //如果这里不使用ctx.channel(),消息发送不出去
                ctx.channel().writeAndFlush(new PingWebSocketFrame());
            } else if (event.state() == IdleState.ALL_IDLE) {
                System.out.println(Constance.ALL_IDLE_TIME + "秒没有出-入站消息了,============>基本铁定已经断开连接了" + RemotingHelper.parseChannelRemoteAddr(ctx.channel()));
            }
        } else {//important 如果这里明确他是一个空闲事件了,就没有必要再把事件往后面传递了,除非后面还有地方要处理这个事件
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String account = Connector.getConnectPool().get(ctx.channel());
        if (account != null) {
            log.info("收到了{}的消息,当前连接数为{}", account, Connector.getConnectPool().size());
        }
        ctx.fireChannelRead(msg);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Connector.getConnectPool().remove(ctx.channel());
        System.out.println("channelInactive:" + RemotingHelper.parseChannelRemoteAddr(ctx.channel()));
        super.channelInactive(ctx);
    }
}
