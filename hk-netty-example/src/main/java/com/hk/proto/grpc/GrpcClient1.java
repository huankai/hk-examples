package com.hk.proto.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * 客户端是单一结果，服务端返回单一结果 (同步)
 *
 * @author huangkai
 * @date 2019-03-27 22:32
 */
public class GrpcClient1 {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",8899)
                .usePlaintext().build();
        // newBlockingStub 同步
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(channel);
        StudentResponse response = blockingStub.getRealNameByName(StudentRequest.newBuilder()
                .setName("admin")
                .build());
        System.out.println(response.getRealName());
    }
}
