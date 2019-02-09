package com.demo.netty;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.Date;

/**
 * {@link Sharable} 标示一个ChannelHandler可以被多个Channel安全地共享
 *
 * @author 揭光智
 * @date 2019/02/08
 */
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 对于每个传入的消息都要调用
     *
     * @param ctx 上下文
     * @param msg 传入的消息
     * @throws Exception 可能有异常
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf in = (ByteBuf) msg;
        System.out.println("服务器接收到来自客户端的消息：" + in.toString(CharsetUtil.UTF_8));
        String sendStr = new Date().toString();
        //write方法会异步发送应答消息给客户端,在调用write()方法时，只会把消息发送到缓冲数组当中，通过调用flush(),会将缓冲区中的消息全部写到
        //SocketChannel中。这里有一个天坑，只能写字节数组这种东西，不能直接写字符串
        ctx.write(Unpooled.copiedBuffer(sendStr.getBytes()));
    }

    /**
     * 通过ChannelInboundHandler最后一次对channelRead()的调用，是当前批量读取中的最后一条消息了
     *
     * @param ctx 上下文
     * @throws Exception 可能有异常
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将缓冲区中的消息刷入SocketChannel中，并关才该Channel
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * 在读取操作期间,有异常抛出时会调用
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //打印异常栈
        cause.printStackTrace();
        //关闭该Channel
        ctx.close();
    }
}
