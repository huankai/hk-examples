/**
 * <pre>
 * java 的 io 中最核心的概念是 流(Stream)，一个流要么是输入流{@link java.io.InputStream}，
 * 要么是输出流{@link java.io.OutputStream}，不可能两者都是，
 * 因为不可能同时继承 InputStream 与 OutputStream 这两个抽象类。
 * </pre>
 *
 * <pre>
 * java 的 nio 中有三个核心概念：
 * {@link java.nio.channels.Selector} :
 * {@link java.nio.channels.Channel}:
 * {@link java.nio.Buffer}:
 * 在 nio 中，我们是面向块 或 缓冲区 编程的。 Buffer 本身就是一块内存，
 * 底层实现上，它实际是一个数组，数据的读与写都是通过 Buffer 实现的
 * </pre>
 *
 * <pre>
 * java 中的基本数据类型除了 boolean 类型外，都有对应 的 Buffer 类型，如 {@link java.nio.IntBuffer} ,
 * {@link java.nio.LongBuffer} , {@link java.nio.ByteBuffer} 等。channel 与Buffer 搭配使用。
 * </pre>
 *
 * <pre>
 *     java nio 中三个重要属性及含义：
 *      capacity: buffer容量 ，每个 Buffer 中的 capacity 不会为负数，也不会变化，与 List 的 size() 不同。
 *
 *      limit: 不能被读与写的第一个元素索引值，永远不会为负数，永远不会超过 capacity 值。
 *
 *      position: 下一个要读或写的元素的索引。不可能为负数，也不会超过 limit 值
 *
 *
 * </pre>
 *
 * @author huangkai
 * @date 2019-03-29 21:21
 */
package com.hk.nio;