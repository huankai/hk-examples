package com.hk.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Nio MappedByte Buffer
 * 1、MappedByteBuffer 可以让文件直接在内存(堆外内存)中修改，操作系统不需要进行拷贝，性能更高
 * {@link java.nio.MappedByteBuffer }
 *
 * @author huangkai
 * @date 2019-03-30 22:12
 */
public class NioTest08 {

    public static void main(String[] args) throws IOException {
        RandomAccessFile accessFile = new RandomAccessFile("file01.txt", "rw");
        FileChannel channel = accessFile.getChannel();
        /*
            参数一： 使用读写模式
            参数二： 可以直接修改的开始位置
            参数三： 映射到内存的大小，即将文件的多少个字节映射到内存,不包括该值
         */
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0, (byte) 5);
        mappedByteBuffer.put(3, (byte) 9);
//        mappedByteBuffer.put(5, (byte) 1); 会报 IndexOutOfBoundsException 异常
        accessFile.close();
    }
}
