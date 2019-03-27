package com.hk.proto.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.time.LocalDateTime;

/**
 * 客户端和服务端都以流的方式将消息发送给对方(异步)
 *
 * @author huangkai
 * @date 2019-03-27 22:32
 */
public class GrpcClient4 {

    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8899)
                .usePlaintext().build();
        // newStub 异步
        StudentServiceGrpc.StudentServiceStub serviceStub = StudentServiceGrpc.newStub(channel);

        StreamObserver<StreamResponse> observer = new StreamObserver<StreamResponse>() {
            @Override
            public void onNext(StreamResponse value) {
                System.out.println(value.getResponseInfo());
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("completed...");
            }
        };
        StreamObserver<StreamRequest> streamObserver = serviceStub.biTalk(observer);
        for (int i = 0; i < 10; i++) {
            streamObserver.onNext(StreamRequest.newBuilder()
                    .setRequestInfo(LocalDateTime.now().toString()).build());
            Thread.sleep(1000);
        }
        Thread.currentThread().join();
    }
}
