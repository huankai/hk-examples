package com.hk.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.security.SecureRandom;

/**
 * io 切换到 nio 示例，从文件 中读取数据
 *
 * @author huangkai
 * @date 2019-03-29 21:21
 */
public class NioTest02 {
    private static final String DIR_PREFIX = "/Users/huangkai/worksplace/hk-examples/hk-netty-example/";

    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream(
                new File(DIR_PREFIX, "file02.txt"));
        FileChannel channel = inputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        channel.read(byteBuffer);
        byteBuffer.flip();
        while (byteBuffer.remaining() > 0) {
            System.out.println("Character:" + byteBuffer.get());
        }
        inputStream.close();
    }
}
