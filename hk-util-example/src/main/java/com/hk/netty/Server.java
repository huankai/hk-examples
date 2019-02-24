package com.hk.netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * 服务端
 *
 * @author huangkai
 * @date 2019/2/11 21:00
 */
public class Server {

    static final int PORT = 10101;

    public static void main(String[] args) throws InterruptedException {
        // 定义服务类
        ServerBootstrap bootstrap = new ServerBootstrap();

        //服务器接受客户端连接
        EventLoopGroup boss = new NioEventLoopGroup();

        //进行网络通讯(网络读写)
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            bootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class) // 指定 NIO 模式
                    .option(ChannelOption.SO_BACKLOG, 1024) //设置 TCP 缓冲区
                    .option(ChannelOption.SO_SNDBUF, 1024 * 32)  //设置发送缓冲区大小
                    .option(ChannelOption.SO_RCVBUF, 1024 * 32)  // 设置接受缓冲区大小
                    .option(ChannelOption.TCP_NODELAY, true) // 关闭延迟发送
                    .option(ChannelOption.SO_KEEPALIVE, true)  //维持链接的活跃，清除死链接
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) {
                            // addLast 方法可配置具体数据接收方法处理
                            ch.pipeline().addLast(new StringDecoder(),
                                    new ServerHandler());
                        }
                    });

            ChannelFuture channelFuture = bootstrap.bind(PORT).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            // 优雅的关闭资源
            boss.shutdownGracefully();
            worker.shutdownGracefully();

        }

    }


    private static class ServerHandler extends ChannelInboundHandlerAdapter {


        /**
         * 读取消息
         *
         * @param ctx ChannelHandler  上下文
         * @param msg 消息内容
         */
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            System.out.println(msg.toString());
        }


        /**
         * 出现异常时
         *
         * @param ctx   ctx
         * @param cause cause
         */
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();

        }
    }
}
