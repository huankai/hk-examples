package com.hk.netty.example.sixth;

import com.hk.protobuf.DataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 泛型类型为 proto 生成的类 {@link DataInfo.Student}
 *
 * @author huangkai
 * @date 2019-03-18 21:52
 */
public class ProtoBufServerHandler extends SimpleChannelInboundHandler<DataInfo.Student> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Student msg) {
        System.out.println(msg.getAddress());
        System.out.println(msg.getName());
        System.out.println(msg.getAge());
    }
}
