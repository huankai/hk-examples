package com.hk.proto.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * GRpc 客户端
 *
 * @author huangkai
 * @date 2019-03-25 22:27
 */
public class GrpcClient {


    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8899).usePlaintext().build();
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(managedChannel);

        StudentResponse response = blockingStub.getRealNameByName(StudentRequest.newBuilder().setName("xianQiang").build());
        System.out.println(response.getRealName());
    }
}
