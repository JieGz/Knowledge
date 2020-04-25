package com.demo.netty.http.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * {@link Sharable} 标示该Handler可以被多个Channel安全共享
 *
 * @author 揭光智
 * @date 2019/02/09
 */
public class ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    /**
     * 连接建立之后马上会被回调
     *
     * @param ctx 上下文
     * @throws Exception 异常
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    }

    /**
     * 当从服务端接收到消息的时候，会被回调.需要注意的是：由服务器发送的消息可能会被分块接收.也就是说,如果服务端发送了5字节，那么不能保证
     * 这5个字节一次性被接收到，即使是对于那么少量的数据，channelRead0（）方法也可能会被调用两次。
     *
     * @param ctx 上下文
     * @param msg 接收到的消息
     * @throws Exception 异常
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("客户端接收到服务器的消息：" + msg.toString(CharsetUtil.UTF_8));
    }

    /**
     * 在处理过程中引发异常时被调用
     *
     * @param ctx   上下文
     * @param cause 异常
     * @throws Exception 异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //打印异常栈
        cause.printStackTrace();
        //关闭该Channel
        ctx.close();
    }
}
