package com.hk.netty.example.fourth;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author huangkai
 * @date 2019-03-17 09:38
 */
public class FourthTestServerInitializer extends ChannelInitializer<SocketChannel> {

    /**
     * channel 被注册时调用
     *
     * @param ch
     * @throws Exception
     */
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
//        IdleStateHandler 闲置状态检测处理器handler
//        参数一：表示客户端在 3 秒内没有执行从服务端读操作，会触发 {@link IdleStateEvent} 事件
//        参数二：表示服务端在 4 秒内没有执行客户端写操作，会触发 {@link IdleStateEvent} 事件
//        参数一：表示客户端在 5 秒内没有执行读和写操作，会触发 {@link IdleStateEvent} 事件
        pipeline.addLast("IdleStateHandler", new IdleStateHandler(3, 4, 5));
        pipeline.addLast("FourthServerHandler", new FourthServerHandler());
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
        super.exceptionCaught(ctx, cause);
    }
}
