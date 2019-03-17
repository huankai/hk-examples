package com.hk.netty.example.second;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;


/**
 * 客户端接收服务器的消息
 *
 * @author huangkai
 * @date 2019-03-17 09:41
 */
public class SecondClientHandler extends SimpleChannelInboundHandler<String> {

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
        ctx.writeAndFlush("客户端发送消息:" + UUID.randomUUID());
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("客户端连接成功时发送...");
        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
