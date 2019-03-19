package com.hk.netty.example.sixth;

import com.hk.protobuf.DataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 泛型类型为 {@link DataInfo.Student}
 *
 * @author huangkai
 * @date 2019-03-18 21:52
 */
public class ProtoBufClientHandler extends SimpleChannelInboundHandler<DataInfo.Student> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Student msg) {

    }

    /**
     * 连接成功时向服务端发送消息
     *
     * @param ctx ctx
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        DataInfo.Student student = DataInfo.Student.newBuilder()
                .setAddress("广州")
                .setAge(19)
                .setName("张三")
                .build();
        ctx.channel().writeAndFlush(student);
    }
}
