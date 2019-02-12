package com.demo.netty.decoder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author 揭光智
 * @date 2019/02/12
 */
public class TimeServer {

    private final int port;

    public TimeServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        //创建一个线程组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //创建服务器的辅助启动器
            ServerBootstrap b = new ServerBootstrap();
            //1: 指定NioEventLoopGroup线程组来接受和处理新的连接
            //2：指定使用的NIO传输Channel
            //3：设置端口号
            //4: 添加一个EchoServerHandler到子ChannelHandler的ChannelPipeline[子Channel]
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new TimeServerChildChannelHandler());
            //异步地绑定服务器，调用sync()方法阻塞等待，直到绑定完成
            ChannelFuture channelFuture = b.bind().sync();
            //获取ChannelFuture的CloseFuture，并且阻塞当前线程直到它完成
            channelFuture.channel().closeFuture().sync();
        } finally {
            //关闭EventLoopGroup释放所有的资源
            bossGroup.shutdownGracefully().sync();
            workerGroup.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        new TimeServer(8887).start();
    }
}
