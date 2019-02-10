package com.hk.oio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 传统socket io，使用线程池
 * 此时，与  {@link OioServer01} 相比，当有其它客户端连接时，
 * 会从线程池中创建或获取新的线程还执行，每个线程各司其责，不会出现阻塞现象。
 *
 * @author huangkai
 * @date 2019/2/10 22:28
 */
public class OioServer02 {

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 创建 sockert ，并监听 10010端口
        ServerSocket serverSocket = new ServerSocket(10010);
        while (true) {
            //获取一个套接字，会阻塞
            Socket socket = serverSocket.accept();
            System.out.println("客户端连接");
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    handerSocket(socket);

                }
            });
        }
    }

    private static void handerSocket(Socket socket) {
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
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
