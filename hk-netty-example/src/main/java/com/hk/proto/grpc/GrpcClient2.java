package com.hk.proto.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

/**
 * 客户端是单一结果，服务端返回多结果(集合) (同步)
 *
 * @author huangkai
 * @date 2019-03-27 22:32
 */
public class GrpcClient2 {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8899)
                .usePlaintext().build();
        // newBlockingStub 同步
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(channel);
        Iterator<UserResponse> response = blockingStub.getUserByName(UserRequest.newBuilder()
                .setName("xxx")
                .build());
        response.forEachRemaining(item -> {
            System.out.println(item.getAge());
            System.out.println(item.getName());
            System.out.println(item.getPhone());
            System.out.println("=========");
        });
    }
}
