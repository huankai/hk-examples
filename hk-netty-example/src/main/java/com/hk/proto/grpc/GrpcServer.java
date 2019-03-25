package com.hk.proto.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * GRpc 服务端
 *
 * @author huangkai
 * @date 2019-03-25 22:26
 */
public class GrpcServer {

    private Server server;

    public static void main(String[] args) throws IOException {
        GrpcServer server = new GrpcServer();
        server.start();
        server.awaitTermination();
    }

    public void start() throws IOException {
        this.server = ServerBuilder.forPort(8899)
                .addService(new StudentServiceImpl()).build().start();
        System.out.println("server start...");

        /* JVM 退出时的回掉钩子*/
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("-------------");
            System.out.println("-----关闭 JVM-------");
            GrpcServer.this.stop();

        }));
        System.out.println(" 执行到这里...");
    }

    public void stop() {
        if (this.server != null) {
            this.server.shutdown();
        }
    }

    /**
     * 等待，如果不执行此方法，启动后就会停止，不会进入阻塞
     */
    private void awaitTermination() {
        if (null != this.server) {
            try {
                this.server.awaitTermination();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
