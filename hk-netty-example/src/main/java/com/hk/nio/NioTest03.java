package com.hk.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * io 切换到 nio 示例: 写数据到文件中。
 *
 * @author huangkai
 * @date 2019-03-29 21:21
 */
public class NioTest03 {

    private static final String DIR_PREFIX = "/Users/huangkai/worksplace/hk-examples/hk-netty-example/";

    public static void main(String[] args) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(
                new File(DIR_PREFIX, "file03.txt"));
        FileChannel channel = outputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        byte[] messages = "Hello ,welcome!!!".getBytes();
        for (byte message : messages) {
            byteBuffer.put(message); // 将数据写入到 byteBuffer 中
        }
        byteBuffer.flip();
        channel.write(byteBuffer); // 将 byteBuffer 中的数据写出到 outputStream 中
        outputStream.close();
    }
}
