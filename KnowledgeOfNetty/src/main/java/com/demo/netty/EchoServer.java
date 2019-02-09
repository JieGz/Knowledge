package com.demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * 当一个新连接创建时,将会有一个新的SocketChannel被创建，ChannelInitializer将会把一个EchoServerHandler的实例绑定到这个新创建的
 * SocketChannel的ChannelPipeline中。EchoServerHandler将会收到有关入站消息的通知，并且它是被@ChannelHandler.Sharable修饰的，所有
 * 它可以被多个Channel安全地共享
 *
 * @author 揭光智
 * @date 2019/02/08
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        final EchoServerHandler serverHandler = new EchoServerHandler();

        //创建一个线程组
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            //创建服务器的辅助启动器
            ServerBootstrap b = new ServerBootstrap();
            //1: 指定NioEventLoopGroup线程组来接受和处理新的连接
            //2：指定使用的NIO传输Channel
            //3：设置端口号
            //4: 添加一个EchoServerHandler到子ChannelHandler的ChannelPipeline
            b.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(serverHandler);
                        }
                    });
            //异步地绑定服务器，调用sync()方法阻塞等待，直到绑定完成
            ChannelFuture channelFuture = b.bind().sync();
            //获取ChannelFuture的CloseFuture，并且阻塞当前线程直到它完成
            channelFuture.channel().closeFuture().sync();
        } finally {
            //关闭EventLoopGroup释放所有的资源
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        new EchoServer(8887).start();
    }
}
