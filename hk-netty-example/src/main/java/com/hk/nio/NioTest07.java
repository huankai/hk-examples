package com.hk.nio;

import java.nio.ByteBuffer;

/**
 * 只读 buffer
 *
 * @author huangkai
 * @date 2019-03-30 22:12
 */
public class NioTest07 {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer(); // 只读 buffer
    }
}
