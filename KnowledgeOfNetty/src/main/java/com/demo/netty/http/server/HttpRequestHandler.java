package com.demo.netty.http.server;

import com.demo.netty.http.common.RemotingUtil;
import com.demo.netty.http.util.Connector;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

/**
 * @author 揭光智
 * @date 2020/04/23
 */
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private final String websocketUrl;

    public HttpRequestHandler(String wsUrl) {
        this.websocketUrl = wsUrl;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        System.out.println("uri:" + request.uri());
        String account;
        if (request.uri().contains(websocketUrl) && (account = "admin") != null) {
            System.out.println("解析出用户的帐号为" + account);
            Connector.getConnectPool().put(ctx.channel(), account);
            String uri = request.uri();
            uri = uri.substring(0, uri.indexOf("?"));
            System.out.println("uri:" + uri);
            request.setUri(uri);
            ctx.fireChannelRead(request.retain());
        } else {
            sendHttpResponse(ctx, request, new DefaultFullHttpResponse(request.protocolVersion(), HttpResponseStatus.FORBIDDEN, ctx.alloc().buffer(0)));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        RemotingUtil.closeChannel(ctx.channel());
        ctx.close();
    }

    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, FullHttpResponse res) {
        // Generate an error page if response getStatus code is not OK (200).
        HttpResponseStatus responseStatus = res.status();
        if (responseStatus.code() != 200) {
            ByteBufUtil.writeUtf8(res.content(), responseStatus.toString());
            HttpUtil.setContentLength(res, res.content().readableBytes());
        }// Send the response and close the connection if necessary.
        boolean keepAlive = HttpUtil.isKeepAlive(req) && responseStatus.code() == 200;
        HttpUtil.setKeepAlive(res, keepAlive);
        ChannelFuture future = ctx.writeAndFlush(res);
        if (!keepAlive) future.addListener(ChannelFutureListener.CLOSE);
    }
}
