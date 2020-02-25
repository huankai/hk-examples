package com.hk.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Nio Client
 */
public class NioTest10Client {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress socketAddress = new InetSocketAddress("localhost", 7000);
        if (!socketChannel.connect(socketAddress)) {
            while (!socketChannel.finishConnect()) {
                System.out.println("因为连接需要时间，客户端不会阻塞 ，可以做其它工作");
            }
        }
        System.out.println("连接成功...");
        ByteBuffer byteBuffer = ByteBuffer.wrap("1".getBytes());
        socketChannel.write(byteBuffer);//将 Buffer 数据写入到 socketChannel
        System.in.read();// 阻塞
    }
}
