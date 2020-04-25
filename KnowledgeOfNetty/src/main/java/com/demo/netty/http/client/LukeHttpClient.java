package com.demo.netty.http.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * @author 揭光智
 * @date 2019/02/09
 */
public class LukeHttpClient {
    private final String host;
    private final int port;


    public LukeHttpClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() {
        //创建一个线程组
        EventLoopGroup group = new NioEventLoopGroup(1);
        try {
            //创建客户端的辅助启动器
            Bootstrap b = new Bootstrap();
            //1: 指定NioEventLoopGroup线程组来接受和处理新的连接
            //2：指定使用的NIO传输Channel
            //3：设置远程连接节点
            //4: 添加一个EchoClientHandler到子ChannelHandler的ChannelPipeline
            b.group(group = new NioEventLoopGroup(1))
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new IdleStateHandler(0, 10, 30, TimeUnit.SECONDS));
                            ch.pipeline().addLast(new ClientHeartBeatHandler());
                            ch.pipeline().addLast(new ClientHandler());
                        }
                    });
            ChannelFuture channelFuture = b.connect();
            channelFuture.addListener((ChannelFutureListener) future -> {
                if (future.isSuccess()) {
                    System.out.println("连接Netty服务器成功");
                } else {
                    System.out.println(LocalDateTime.now().format(
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ":连接服务器失败,开始尝试重连");
                    ConnectUtil.connect(future.channel());
                }
            });
            //获取ChannelFuture的CloseFuture，并且阻塞当前线程直到它完成
            // channelFuture.channel().closeFuture().syncUninterruptibly();
        } finally {
            //关闭EventLoopGroup释放所有的资源
            //  group.shutdownGracefully().syncUninterruptibly();
        }
    }

    public static void main(String[] args) throws Exception {
        LukeHttpClient client = new LukeHttpClient("127.0.0.1", 8080);
        ConnectUtil.initClient(client);
        client.start();
    }
}
