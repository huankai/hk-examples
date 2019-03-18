package com.hk.netty.example.sixth;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 泛型类型为
 *
 * @author huangkai
 * @date 2019-03-18 21:52
 */
public class ProtoBufServerHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

    }
}
