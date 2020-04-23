package com.demo.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.ImmediateEventExecutor;

import java.net.InetSocketAddress;

/**
 * @author 揭光智
 * @date 2020/04/22
 */
public class LukeHttpServer {
    /** 端口 */
    private final int port;

    private final ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);

    public LukeHttpServer(int port) throws Exception {
        this.port = port;
    }

    public void start() throws Exception {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(8);
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new LuckServerInitializer(channelGroup));
            //异步地绑定服务器，调用sync()方法阻塞等待，直到绑定完成
            ChannelFuture channelFuture = bootstrap.bind().sync();
            //获取ChannelFuture的CloseFuture，并且阻塞当前线程直到它完成
            channelFuture.channel().closeFuture().sync();

        } finally {
            channelGroup.close();
            workerGroup.shutdownGracefully().sync();
            bossGroup.shutdownGracefully().sync();
        }
    }
}

