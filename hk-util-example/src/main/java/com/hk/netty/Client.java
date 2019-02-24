package com.hk.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 客户端
 *
 * @author huangkai
 * @date 2019/2/17 11:28
 */
public class Client {

    public static void main(String[] args) {
        Bootstrap clientBootstrap = new Bootstrap();

        // 定义执行线程组
        EventLoopGroup worker = new NioEventLoopGroup();

        //设置线程池
        clientBootstrap.group(worker);

        //设置通道
        clientBootstrap.channel(NioServerSocketChannel.class);

        //添加Handler
        clientBootstrap.handler(new ChannelInitializer<ServerChannel>() {
            @Override
            protected void initChannel(ServerChannel channel) {
                System.out.println("client channel init!");
                ChannelPipeline pipeline = channel.pipeline();
                pipeline.addLast("StringDecoder", new StringDecoder());
                pipeline.addLast("StringEncoder", new StringEncoder());
                pipeline.addLast("ClientHandler", new ClientHandler());
            }
        });

        //建立连接
        ChannelFuture channelFuture = clientBootstrap.connect("0.0.0.0", Server.PORT);
        try {
            //测试输入
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.println("请输入：");
                String msg = bufferedReader.readLine();
                channelFuture.channel().writeAndFlush(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            worker.shutdownGracefully();
        }
    }

    private static class ClientHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("Client read message:" + String.valueOf(msg));
        }
    }
}
