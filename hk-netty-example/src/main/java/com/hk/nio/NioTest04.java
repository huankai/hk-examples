package com.hk.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 使用 nio 将源文件内容写出到新文件中
 *
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
            byteBuffer.clear(); //清空 buffer，如果不执行此方法，会导致死循环。
            int read = inputStreamChannel.read(byteBuffer); // inputStream 读取内容写入 buffer 中
            System.out.println("read" + read);
            if (read == -1) {
                break;
            }
            byteBuffer.flip();
            outputStreamChannel.write(byteBuffer); // buffer 中读取内容写出到 outputStream 中
        }
        inputStreamChannel.close();
        outputStreamChannel.close();

    }
}
