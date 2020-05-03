package com.demo.netty.http.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketClientCompressionHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.net.InetSocketAddress;
import java.net.URI;
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
            URI uri = new URI("ws://127.0.0.1:8888/ws?token=admin");
            final ClientHandler handler = new ClientHandler(WebSocketClientHandshakerFactory.newHandshaker(uri, WebSocketVersion.V13, null
                    , true, new DefaultHttpHeaders()));

            //创建客户端的辅助启动器
            Bootstrap b = new Bootstrap();
            //1: 指定NioEventLoopGroup线程组来接受和处理新的连接
            //2：指定使用的NIO传输Channel
            //3：设置远程连接节点
            //4: 添加一个EchoClientHandler到子ChannelHandler的ChannelPipeline
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new IdleStateHandler(90, 60, 0, TimeUnit.SECONDS));
                            ch.pipeline().addLast(new ClientHeartBeatHandler());
                            ch.pipeline().addLast(new HttpClientCodec());
                            ch.pipeline().addLast(new HttpObjectAggregator(8192));
                            ch.pipeline().addLast(WebSocketClientCompressionHandler.INSTANCE);
                            ch.pipeline().addLast(handler);
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
        } catch (Exception e) {
            e.printStackTrace();
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
