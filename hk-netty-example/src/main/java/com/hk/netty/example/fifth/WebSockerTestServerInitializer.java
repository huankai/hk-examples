package com.hk.netty.example.fifth;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author huangkai
 * @date 2019-03-17 09:38
 */
public class WebSockerTestServerInitializer extends ChannelInitializer<SocketChannel> {

    /**
     * channel 被注册时调用
     *
     * @param ch
     * @throws Exception
     */
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("HttpServerCodec", new HttpServerCodec());
        pipeline.addLast("ChunkedWriteHandler", new ChunkedWriteHandler());
        //
        pipeline.addLast("HttpObjectAggregator", new HttpObjectAggregator(8192));

        //
        pipeline.addLast("WebSocketServerProtocolHandler", new WebSocketServerProtocolHandler("/ws"));
        pipeline.addLast("TextWebsocketServerHandler",new TextWebsocketServerHandler());
    }

    /**
     * 出现异常时调用
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
