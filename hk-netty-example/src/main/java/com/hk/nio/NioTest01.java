package com.hk.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * @author huangkai
 * @date 2019-03-29 21:21
 */
public class NioTest01 {

    public static void main(String[] args) {
        // 创建一个 buffer ,大小为 10 ,可存放 10 个 int
        IntBuffer buffer = IntBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            int anInt = new SecureRandom().nextInt(20);
            buffer.put(anInt);//将数据写入到 buffer 中
        }
        buffer.flip(); // 实现状态分转，读写切换。在进行切换前，一定要调用 此方法，否则数据会出错。
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get()); // 从 buffer 中读取数据
        }
    }
}
