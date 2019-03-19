package com.hk.netty.example.sixth;

import com.hk.protobuf.DataInfo;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * @author huangkai
 * @date 2019-03-18 21:51
 */
public class ProtoBufClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new ProtobufVarint32FrameDecoder(),
                new ProtobufDecoder(DataInfo.Student.getDefaultInstance()),//解码器，
                new ProtobufVarint32LengthFieldPrepender(),
                new ProtobufEncoder(),
                new ProtoBufClientHandler());
    }
}
