package com.hk.netty.example.third;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;


/**
 * @author huangkai
 * @date 2019-03-17 09:41
 */
public class ThirdServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 保存所有与服务器连接的 Channel 对象
     */
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 读取客户端发送的请求，并响应
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.forEach(item -> {
            if (item.equals(channel)) {
                item.writeAndFlush("来自[自己]的消息 :" + msg + "\r\n");
            } else {
                item.writeAndFlush("来自[" + channel.remoteAddress() + "]的消息：" + msg + "\r\n");
            }
        });
    }

    /**
     * 当用户连接成功时回掉
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //会循环将 channelGroup 中所有的channel 写入数据。
        channelGroup.writeAndFlush("服务器 ：" + channel.remoteAddress() + "加入\n");
        channelGroup.add(channel);
    }

    /**
     * 客户端连接断开时调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("服务器 ：" + channel.remoteAddress() + "断开\n");
//        channelGroup.remove(channel);// netty 会自动调用，将连接断开的channel 从channelGroup 中删除，
        System.out.println("当前在线用户数：" + channelGroup.size());
    }

    /**
     * 连接处于活动状态
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "上线了...");
    }

    /**
     * 连接处于非活动状态
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "下线了......");
    }
}
