package com.hk.netty.example.third;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 03:使用 netty 实现多客户端连接，实现消息通讯
 * <pre>
 * 案例：
 * A/B/C 三个用户，当 只有一个用户连接时，控制台打印此用户连接成功的消息
 * 当 第二个用户连接成功时，控制台打印此用户连接成功的消息，并广播给第一个连接成功的用户；
 * 当 第三个用户连接成功时，控制台打印此用户连接成功的消息，并广播给其他连接成功的用户；
 * 当 有用户断开连接时，控制台打印此用户断开连接的消息，并广播给其他连接成功的用户；
 *
 * 启动 {@link ThirdTestServer} ，再分别启动多个 {@link ThirdTestClient}，查看控制台打印信息
 * </pre>
 *
 * @author huangkai
 * @date 2019-03-17 09:33
 */
public class ThirdTestServer {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();//接收连接
        EventLoopGroup workerGroup = new NioEventLoopGroup(); // 完成连接的处理
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ThirdTestServerInitializer());

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
