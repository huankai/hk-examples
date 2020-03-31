package com.hk.netty.example.first;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 01： netty http 简单实现
 * <pre>
 *
 * 服务端，启动后，使用浏览器访问 http://localhost:8899 或使用命令执行： curl  http://localhost:8899
 * </pre>
 *
 * @author huangkai
 * @date 2019-03-17 09:33
 */
public class FirstTestServer {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();//接收连接
        EventLoopGroup workerGroup = new NioEventLoopGroup(); // 完成连接的处理
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .option(ChannelOption.SO_BACKLOG, 128) // 设置线程队列连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true) // 设置保持活动连接状态
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new FirstTestServerInitializer());

            // 绑定端口，并且同步处理
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();

//            对关闭通道进行监听，当有关闭通过的时候才会被处理
            channelFuture.channel().closeFuture().sync();

        } finally {
            //关闭资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


}
