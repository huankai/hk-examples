package com.hk.nio;

import java.nio.ByteBuffer;

/**
 * buffer slice 方法生成的 buffer 与原来的 buffer共享相同的底层数组。
 *
 * @author huangkai
 * @date 2019-03-30 22:12
 */
public class NioTest06 {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        buffer.position(2).limit(6); //将 Buffer 的 position 标记为 2, limit 标记为 6
        ByteBuffer slice = buffer.slice(); // 生成 slice Buffer
        for (int i = 0; i < slice.capacity(); i++) {
            byte b = slice.get(i);
            b *= 2;
            slice.put(i, b); // 修改 slice buffer 指定位置的值，因为会共享 原始 Buffer 的数组，
        }
        buffer.position(0).limit(buffer.capacity());
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());  // 所以在这里打印的结果也是 slice buffer 修改后的值。
        }
    }
}
