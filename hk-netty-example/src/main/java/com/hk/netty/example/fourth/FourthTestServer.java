package com.hk.netty.example.fourth;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * 04:netty 心跳检查，长链接
 * <pre>
 * 启动 {@link FourthTestServer} ，再启动 {@link FourthTestClient}
 * 如果 client 在 3 秒{@link FourthTestServerInitializer#initChannel(SocketChannel)} 内没有发送数据到服务器，会打印 读空闲 信息
 * 如果 client 一直在发送消息，而服务器在 4 秒内没有发送数据给客户端，会打印 写空闲 信息
 *
 * 其主要是使用 {@link IdleStateHandler} 来实现的。
 * </pre>
 *
 * @author huangkai
 * @date 2019-03-17 09:33
 */
public class FourthTestServer {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();//接收连接
        EventLoopGroup workerGroup = new NioEventLoopGroup(); // 完成连接的处理
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))// handler是争对 bossGroup的。
                    .childHandler(new FourthTestServerInitializer());// childHandler 是争对 workerGroup 的。

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
