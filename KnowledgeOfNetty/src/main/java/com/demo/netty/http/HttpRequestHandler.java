package com.demo.netty.http;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedNioFile;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author 揭光智
 * @date 2020/04/23
 */
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private final String wsUrl;
    private static final File INDEX;

    static {
        URL location = HttpRequestHandler.class.getProtectionDomain()
                .getCodeSource().getLocation();
        try {
            String path = location.toURI() + "index.html";
            path = !path.contains("file:") ? path : path.substring(5);
            System.out.println("path:" + path);
            INDEX = new File(path);
        } catch (URISyntaxException e) {
            throw new IllegalStateException(
                    "Unable to locate index.html", e);
        }
    }

    public HttpRequestHandler(String wsUrl) {
        this.wsUrl = wsUrl;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        //如果请求了WebSocket协议升级,调用retain()方法增加引用计数,并将request传递给下一个ChanelInboundHandler.
        //这里之所有要调用retain()方法,是因为channelRead0()方法完成之后,它将调用FullHttpRequest对象上的release()方法释放资源
        System.out.println(request.uri());

        if (request.uri().contains(wsUrl)) {
            if (request.uri().contains("?")) {
                String[] uris = request.uri().split("\\?");
                request.setUri(uris[0]);
                System.out.println("token:" + uris[1]);
                ctx.fireChannelRead(request.retain());
            } else {
                HttpResponse response = new DefaultHttpResponse(request.protocolVersion(), HttpResponseStatus.FORBIDDEN);
                response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
                ctx.write(response);
                ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
            }
        } else {
            //处理100Continue请求,以符合Http1.1规范
            if (HttpUtil.is100ContinueExpected(request)) {
                send100Continue(ctx);
            }
            //读取index.html
            RandomAccessFile file = new RandomAccessFile(INDEX, "r");
            HttpResponse response = new DefaultHttpResponse(request.protocolVersion(), HttpResponseStatus.OK);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
            //如果请求了keep-alive,则添加所需的Http头信息
            boolean keepAlive = HttpUtil.isKeepAlive(request);
            if (keepAlive) {
                response.headers().set(HttpHeaderNames.CONTENT_LENGTH, file.length());
                // response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
                response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
            }
            //请HttpResponse写到客户端
            ctx.write(response);
            //写index.html到客户端
            if (ctx.pipeline().get(SslHandler.class) == null) {
                ctx.write(new DefaultFileRegion(file.getChannel(), 0, file.length()));
            } else {
                ctx.write(new ChunkedNioFile(file.getChannel()));
            }
            //写LastHttpContent并冲刷至客户端
            ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
            //如果没有请求keep-alive,则在写操作完成后关闭Handler
            if (!keepAlive) {
                future.addListener(ChannelFutureListener.CLOSE);
            }
        }
    }

    private void send100Continue(ChannelHandlerContext ctx) {
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE);
        ctx.writeAndFlush(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
