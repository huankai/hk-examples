package com.hk.netty.example.first;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author huangkai
 * @date 2019-03-17 09:38
 */
public class FirstTestServerInitializer extends ChannelInitializer<SocketChannel> {

    /**
     * channel 被注册时调用
     *
     * @param ch
     */
    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        pipeline.addLast("firstHttpServerHandler", new FirstHttpServerHandler());
    }
}
