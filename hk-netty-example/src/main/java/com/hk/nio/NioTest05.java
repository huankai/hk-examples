package com.hk.nio;

import java.nio.ByteBuffer;

/**
 * buffer 类型化 put 与 get 方法，注意 put 的类型要与 get 的顺序一致，否则出现数据错乱。
 *
 * @author huangkai
 * @date 2019-03-30 22:12
 */
public class NioTest05 {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);
        buffer.putChar('你').putInt(10).putDouble(3.44).putLong(10000342413L).putDouble(18.4323).putFloat(12.4f).putShort((short) 23);
        buffer.flip();
        System.out.println(buffer.getChar());
        System.out.println(buffer.getInt());
        System.out.println(buffer.getDouble());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getDouble());
        System.out.println(buffer.getFloat());
        System.out.println(buffer.getShort());
    }
}
