package com.hk.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author huangkai
 * @date 2019-03-30 22:12
 */
public class NioTest04 {

    private static final String DIR_PREFIX = "/Users/huangkai/worksplace/hk-examples/hk-netty-example/";

    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream(
                new File(DIR_PREFIX, "input.txt"));
        FileOutputStream outputStream = new FileOutputStream(
                new File(DIR_PREFIX, "output.txt"));
        FileChannel inputStreamChannel = inputStream.getChannel();
        FileChannel outputStreamChannel = outputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        while (true) {
            byteBuffer.clear(); //清空 buffer
            int read = inputStreamChannel.read(byteBuffer);
            System.out.println("read" + read);
            if (read == -1) {
                break;
            }
            byteBuffer.flip();
            outputStreamChannel.write(byteBuffer);
        }
        inputStreamChannel.close();
        outputStreamChannel.close();

    }
}
