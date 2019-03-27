package com.hk.proto.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;



/**
 * 客户端是流，服务端返回单一结果(异步)
 *
 * @author huangkai
 * @date 2019-03-27 22:32
 */
public class GrpcClient3 {

    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8899)
                .usePlaintext().build();
        // newStub 异步
        StudentServiceGrpc.StudentServiceStub serviceStub = StudentServiceGrpc.newStub(channel);

        StreamObserver<UserResponseList> observer = new StreamObserver<UserResponseList>() {
            @Override
            public void onNext(UserResponseList value) {
                value.getUserResponseList().forEach(item -> {
                    System.out.println(item.getPhone());
                    System.out.println(item.getName());
                    System.out.println(item.getAge());
                    System.out.println("========");
                });
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
        StreamObserver<UserRequest> streamObserver = serviceStub.getUserWrapperByName(observer);
        for (int i = 0; i < 10; i++) {
            streamObserver.onNext(UserRequest.newBuilder()
                    .setName("admin").build());
            Thread.sleep(1000);
        }
        streamObserver.onCompleted();

        Thread.currentThread().join();


    }
}
