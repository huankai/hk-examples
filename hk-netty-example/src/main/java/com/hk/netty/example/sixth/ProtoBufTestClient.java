package com.hk.netty.example.sixth;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;


/**
 * @author huangkai
 * @date 2019-3-19 13:06
 */
public class ProtoBufTestClient {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup loopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(loopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ProtoBufClientInitializer())
                    .connect("localhost", 8899).sync();


        } finally {
            //关闭资源
            loopGroup.shutdownGracefully();
        }
    }
}
