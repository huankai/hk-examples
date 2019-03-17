package com.hk.netty.example.second;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author huangkai
 * @date 2019-03-17 09:33
 */
public class SecondTestClient {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup loopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            ChannelFuture channelFuture = bootstrap.group(loopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new SecondTestClilentInitializer())
                    .connect("localhost", 8899).sync();

            channelFuture.channel().closeFuture().sync();

        } finally {
            //关闭资源
            loopGroup.shutdownGracefully();
        }
    }


}
