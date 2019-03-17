package com.hk.netty.example.fifth;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;

/**
 * web socket 文本帧处理
 *
 * @author huangkai
 * @date 2019-03-17 21:12
 */
public class TextWebsocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("收到消息:" + msg.text());
        //注意：这里 writeAndFlush 的参数类型要为  TextWebSocketFrame
        ctx.channel().writeAndFlush(new TextWebSocketFrame("当前时间:" + LocalDateTime.now()));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().id().asLongText());//长channelId，全局唯一
        System.out.println(ctx.channel().id().asShortText());//短channelId，全局不唯一
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
