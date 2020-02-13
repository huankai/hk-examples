package com.hk.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;

/**
 * Scattering : 将数据写入到 buffer 时，可以采用 buffer 数组，依次写入，[分散]
 * Gathering: 从 buffer 读取数据时，可以采用 buffer 数组，依次读
 *
 * @author huangkai
 * @date 2019-03-30 22:12
 */
public class NioTest09 {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);
        serverSocketChannel.socket().bind(inetSocketAddress);

    }
}
