package com.hk.netty.example.second;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;


/**
 * @author huangkai
 * @date 2019-03-17 09:41
 */
public class SecondServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 读取客户端发送的请求，并响应
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "," + msg);
        ctx.writeAndFlush("for Server" + UUID.randomUUID());
    }
}
