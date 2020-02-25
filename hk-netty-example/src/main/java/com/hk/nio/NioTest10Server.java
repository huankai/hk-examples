package com.hk.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Nio Server
 */
public class NioTest10Server {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        Selector selector = Selector.open();
        // 绑定端口在服务端监听
        serverSocketChannel.socket().bind(new InetSocketAddress(7000));
        // 设置为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        // 把 serverSocketChannel 注册到 selector 中，关注的事件为 OP_ACCEPT(连接事件)
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //循环等待客户端连接
        for (; ; ) {
            if (selector.select(1000) == 0) { //等待 1秒中，如果还没有 OP_ACCEPT 事件发生
                System.out.println("服务器等待了 1秒，无连接...");
            } else {
                // 获取到关注事件的 selectionKeys
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isAcceptable()) { // 获取到连接事件
                        // 给该客户端生成 socketChannel ，在这里该方法不会阻塞
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false); //设置为 非阻塞
                        // 将 socketChannel 注册到 selector，关注事件为 OP_READ
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    }
                    if (selectionKey.isReadable()) { //  获取到读 (OP_READ)的事件
                        SocketChannel channel = (SocketChannel) selectionKey.channel();//通过  selectionKey 反向获取 SelectableChannel
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int len;
                        while ((len = channel.read(buffer)) > 0) {  //sChannel 读取数据到ByteBuffer
                            buffer.flip(); //切换到读取模式
                            String str = new String(buffer.array(), 0, len);  //读取
                            System.out.println("------from Client:" + str);
                            buffer.clear();
                        }
//                        ByteBuffer attachment = (ByteBuffer) selectionKey.attachment(); //获取该 channel 关联的 ByteBuffer
//                        channel.read(attachment); //
//                        System.out.println("------from Client:" + new String(attachment.array()));
                    }
                    // 手动从当前集合中移除当前的 selectionKeys ，防止重复操作
                    iterator.remove();
                }
            }
        }
    }
}
