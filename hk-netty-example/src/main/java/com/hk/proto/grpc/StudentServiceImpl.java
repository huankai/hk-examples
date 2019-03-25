package com.hk.proto.grpc;

import io.grpc.stub.StreamObserver;

/**
 * @author huangkai
 * @date 2019-03-25 22:20
 */
public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {

    @Override
    public void getRealNameByName(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {
        System.out.println("name:" + request.getName());

        /* 此方法没有返回值，需要使用 StreamObserver 返回数据客户端*/
        StudentResponse response = StudentResponse.newBuilder().setRealName("Hello:" + request.getName()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();// 告诉客户端，方法执行完了。
    }
}
