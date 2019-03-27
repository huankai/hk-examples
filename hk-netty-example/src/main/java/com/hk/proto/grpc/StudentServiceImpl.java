package com.hk.proto.grpc;

import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Override
    public void getUserByName(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        System.out.println("name:" + request.getName());
        responseObserver.onNext(UserResponse.newBuilder()
                .setName("admin")
                .setAge(10)
                .setPhone("18800000000").build());
        responseObserver.onNext(UserResponse.newBuilder()
                .setName("admin2")
                .setAge(10)
                .setPhone("18800000002").build());
        responseObserver.onNext(UserResponse.newBuilder()
                .setName("admin3")
                .setAge(10)
                .setPhone("18800000003").build());
        responseObserver.onCompleted();

    }

    @Override
    public StreamObserver<UserRequest> getUserWrapperByName(StreamObserver<UserResponseList> responseObserver) {
        return new StreamObserver<UserRequest>() {

            private List<UserRequest> requests = new ArrayList<>();

            @Override
            public void onNext(UserRequest value) {
                System.out.println("name: " + value.getName());
                requests.add(value);
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                List<UserResponse> responses = new ArrayList<>();
                for (UserRequest request : requests) {
                    responses.add(UserResponse.newBuilder()
                            .setPhone(request.getName())
                            .setAge(10)
                            .setName(request.getName())
                            .build());
                }
                responseObserver.onNext(UserResponseList.newBuilder().addAllUserResponse(responses).build());
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<StreamRequest> biTalk(StreamObserver<StreamResponse> responseObserver) {
        return new StreamObserver<StreamRequest>() {
            @Override
            public void onNext(StreamRequest value) {
                System.out.println("requestInfo:" + value.getRequestInfo());
                responseObserver.onNext(StreamResponse.newBuilder()
                        .setResponseInfo(UUID.randomUUID().toString())
                        .build());
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
