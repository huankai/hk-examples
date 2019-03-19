package com.hk.netty.example.sixth;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 06: netty protobuf 示例:
 * <pre>
 *     1、启动 {@link ProtoBufTestServer}，再启动 {@link ProtoBufTestClient}
 *
 *     注意： client 与 server 使用 netty 通信，应该分别使用两个进程来启动，
 *      所以 使用 protoc 生成的类需要单独打成包，以供 client 与 server 共同引用。
 * </pre>
 *
 * @author huangkai
 * @date 2019-03-18 21:50
 */
public class ProtoBufTestServer {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();//接收连接
        EventLoopGroup workerGroup = new NioEventLoopGroup(); // 完成连接的处理
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))// handler是争对 bossGroup的。
                    .childHandler(new ProtoBufServerInitializer());// childHandler 是争对 workerGroup 的。

            // 绑定端口
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();

            channelFuture.channel().closeFuture().sync();
        } finally {
            //关闭资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
