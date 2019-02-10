package com.hk.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author kally
 * @date 2018/12/18 23 42
 */
public class NIOServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);
        System.out.println("NIO Server has started.Listening on port:" + serverSocketChannel.getLocalAddress());
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (true) {
            int select = selector.select();
            if (select != 0) {

                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel clientChannel = channel.accept();
                        System.out.println("Connection from :" + clientChannel.getLocalAddress());

                        clientChannel.configureBlocking(false);
                        clientChannel.register(selector, SelectionKey.OP_READ);
                    }
                    if (selectionKey.isReadable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();

                        int read = channel.read(byteBuffer);
                        if (read > 0) {
                            String request = new String(byteBuffer.array()).trim();
                            byteBuffer.clear();
                            System.out.println("From " + channel.getRemoteAddress() + " : " + request);
                            channel.write(ByteBuffer.wrap(request.getBytes()));

                        } else {
                            System.out.println("客户端关闭连接");
                            selectionKey.cancel();
                        }
                    }
                    iterator.remove();
                }
            }
        }


    }


}
