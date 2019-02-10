package com.hk.oio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 传统socket io，使用单线程：
 *
 * 如果有一个客户端连接，则其它客户端连接会出现阻塞现象。
 * 可以使用 cmd 执行 telnet 127.0.0.1 10010 连接后查看，
 * 如果每一个终端连接成功后，第二个 cmd 则会出现阻塞。
 *
 * @author huangkai
 * @date 2019/2/10 22:28
 */
public class OioServer01 {

    public static void main(String[] args) throws IOException {
        // 创建 sockert ，并监听 10010端口
        ServerSocket serverSocket = new ServerSocket(10010);
        while (true) {
            //获取一个套接字，会阻塞
            Socket socket = serverSocket.accept();
            System.out.println("客户端连接");
            handerSocket(socket);
        }
    }

    private static void handerSocket(Socket socket) throws IOException {
        byte[] bytes = new byte[1024];
        try {
            InputStream socketInputStream = socket.getInputStream();
            while (true) {
                //会阻塞
                int read = socketInputStream.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }
}
