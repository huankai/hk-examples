package com.hk.proto.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.19.0)",
    comments = "Source: Student.proto")
public final class StudentServiceGrpc {

  private StudentServiceGrpc() {}

  public static final String SERVICE_NAME = "com.hk.proto.StudentService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.hk.proto.grpc.StudentRequest,
      com.hk.proto.grpc.StudentResponse> getGetRealNameByNameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getRealNameByName",
      requestType = com.hk.proto.grpc.StudentRequest.class,
      responseType = com.hk.proto.grpc.StudentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.hk.proto.grpc.StudentRequest,
      com.hk.proto.grpc.StudentResponse> getGetRealNameByNameMethod() {
    io.grpc.MethodDescriptor<com.hk.proto.grpc.StudentRequest, com.hk.proto.grpc.StudentResponse> getGetRealNameByNameMethod;
    if ((getGetRealNameByNameMethod = StudentServiceGrpc.getGetRealNameByNameMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getGetRealNameByNameMethod = StudentServiceGrpc.getGetRealNameByNameMethod) == null) {
          StudentServiceGrpc.getGetRealNameByNameMethod = getGetRealNameByNameMethod = 
              io.grpc.MethodDescriptor.<com.hk.proto.grpc.StudentRequest, com.hk.proto.grpc.StudentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.hk.proto.StudentService", "getRealNameByName"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.hk.proto.grpc.StudentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.hk.proto.grpc.StudentResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("getRealNameByName"))
                  .build();
          }
        }
     }
     return getGetRealNameByNameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.hk.proto.grpc.UserRequest,
      com.hk.proto.grpc.UserResponse> getGetUserByNameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getUserByName",
      requestType = com.hk.proto.grpc.UserRequest.class,
      responseType = com.hk.proto.grpc.UserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.hk.proto.grpc.UserRequest,
      com.hk.proto.grpc.UserResponse> getGetUserByNameMethod() {
    io.grpc.MethodDescriptor<com.hk.proto.grpc.UserRequest, com.hk.proto.grpc.UserResponse> getGetUserByNameMethod;
    if ((getGetUserByNameMethod = StudentServiceGrpc.getGetUserByNameMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getGetUserByNameMethod = StudentServiceGrpc.getGetUserByNameMethod) == null) {
          StudentServiceGrpc.getGetUserByNameMethod = getGetUserByNameMethod = 
              io.grpc.MethodDescriptor.<com.hk.proto.grpc.UserRequest, com.hk.proto.grpc.UserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.hk.proto.StudentService", "getUserByName"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.hk.proto.grpc.UserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.hk.proto.grpc.UserResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("getUserByName"))
                  .build();
          }
        }
     }
     return getGetUserByNameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.hk.proto.grpc.UserRequest,
      com.hk.proto.grpc.UserResponseList> getGetUserWrapperByNameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getUserWrapperByName",
      requestType = com.hk.proto.grpc.UserRequest.class,
      responseType = com.hk.proto.grpc.UserResponseList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.hk.proto.grpc.UserRequest,
      com.hk.proto.grpc.UserResponseList> getGetUserWrapperByNameMethod() {
    io.grpc.MethodDescriptor<com.hk.proto.grpc.UserRequest, com.hk.proto.grpc.UserResponseList> getGetUserWrapperByNameMethod;
    if ((getGetUserWrapperByNameMethod = StudentServiceGrpc.getGetUserWrapperByNameMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getGetUserWrapperByNameMethod = StudentServiceGrpc.getGetUserWrapperByNameMethod) == null) {
          StudentServiceGrpc.getGetUserWrapperByNameMethod = getGetUserWrapperByNameMethod = 
              io.grpc.MethodDescriptor.<com.hk.proto.grpc.UserRequest, com.hk.proto.grpc.UserResponseList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.hk.proto.StudentService", "getUserWrapperByName"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.hk.proto.grpc.UserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.hk.proto.grpc.UserResponseList.getDefaultInstance()))
                  .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("getUserWrapperByName"))
                  .build();
          }
        }
     }
     return getGetUserWrapperByNameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.hk.proto.grpc.StreamRequest,
      com.hk.proto.grpc.StreamResponse> getBiTalkMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "biTalk",
      requestType = com.hk.proto.grpc.StreamRequest.class,
      responseType = com.hk.proto.grpc.StreamResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.hk.proto.grpc.StreamRequest,
      com.hk.proto.grpc.StreamResponse> getBiTalkMethod() {
    io.grpc.MethodDescriptor<com.hk.proto.grpc.StreamRequest, com.hk.proto.grpc.StreamResponse> getBiTalkMethod;
    if ((getBiTalkMethod = StudentServiceGrpc.getBiTalkMethod) == null) {
      synchronized (StudentServiceGrpc.class) {
        if ((getBiTalkMethod = StudentServiceGrpc.getBiTalkMethod) == null) {
          StudentServiceGrpc.getBiTalkMethod = getBiTalkMethod = 
              io.grpc.MethodDescriptor.<com.hk.proto.grpc.StreamRequest, com.hk.proto.grpc.StreamResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.hk.proto.StudentService", "biTalk"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.hk.proto.grpc.StreamRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.hk.proto.grpc.StreamResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new StudentServiceMethodDescriptorSupplier("biTalk"))
                  .build();
          }
        }
     }
     return getBiTalkMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static StudentServiceStub newStub(io.grpc.Channel channel) {
    return new StudentServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static StudentServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new StudentServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static StudentServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new StudentServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class StudentServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *一、 客户端是单一结果，服务端返回单一结果
     * </pre>
     */
    public void getRealNameByName(com.hk.proto.grpc.StudentRequest request,
        io.grpc.stub.StreamObserver<com.hk.proto.grpc.StudentResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetRealNameByNameMethod(), responseObserver);
    }

    /**
     * <pre>
     * 二、客户端是单一结果，服务端返回多结果(集合)
     * 注意，这里请求参数不能为int32，即使只有一个参数，也要用 message 定义成对象，这是和 thrift 不同之处
     *    rpc getUserByName2 (int32) returns (stream UserResponse) {}
     * </pre>
     */
    public void getUserByName(com.hk.proto.grpc.UserRequest request,
        io.grpc.stub.StreamObserver<com.hk.proto.grpc.UserResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetUserByNameMethod(), responseObserver);
    }

    /**
     * <pre>
     * 三、客户端是流，服务端返回单一结果
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.hk.proto.grpc.UserRequest> getUserWrapperByName(
        io.grpc.stub.StreamObserver<com.hk.proto.grpc.UserResponseList> responseObserver) {
      return asyncUnimplementedStreamingCall(getGetUserWrapperByNameMethod(), responseObserver);
    }

    /**
     * <pre>
     * 四、客户端和服务端都以流的方式将消息发送给对方
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.hk.proto.grpc.StreamRequest> biTalk(
        io.grpc.stub.StreamObserver<com.hk.proto.grpc.StreamResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getBiTalkMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetRealNameByNameMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.hk.proto.grpc.StudentRequest,
                com.hk.proto.grpc.StudentResponse>(
                  this, METHODID_GET_REAL_NAME_BY_NAME)))
          .addMethod(
            getGetUserByNameMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.hk.proto.grpc.UserRequest,
                com.hk.proto.grpc.UserResponse>(
                  this, METHODID_GET_USER_BY_NAME)))
          .addMethod(
            getGetUserWrapperByNameMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                com.hk.proto.grpc.UserRequest,
                com.hk.proto.grpc.UserResponseList>(
                  this, METHODID_GET_USER_WRAPPER_BY_NAME)))
          .addMethod(
            getBiTalkMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.hk.proto.grpc.StreamRequest,
                com.hk.proto.grpc.StreamResponse>(
                  this, METHODID_BI_TALK)))
          .build();
    }
  }

  /**
   */
  public static final class StudentServiceStub extends io.grpc.stub.AbstractStub<StudentServiceStub> {
    private StudentServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StudentServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StudentServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *一、 客户端是单一结果，服务端返回单一结果
     * </pre>
     */
    public void getRealNameByName(com.hk.proto.grpc.StudentRequest request,
        io.grpc.stub.StreamObserver<com.hk.proto.grpc.StudentResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetRealNameByNameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * 二、客户端是单一结果，服务端返回多结果(集合)
     * 注意，这里请求参数不能为int32，即使只有一个参数，也要用 message 定义成对象，这是和 thrift 不同之处
     *    rpc getUserByName2 (int32) returns (stream UserResponse) {}
     * </pre>
     */
    public void getUserByName(com.hk.proto.grpc.UserRequest request,
        io.grpc.stub.StreamObserver<com.hk.proto.grpc.UserResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetUserByNameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * 三、客户端是流，服务端返回单一结果
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.hk.proto.grpc.UserRequest> getUserWrapperByName(
        io.grpc.stub.StreamObserver<com.hk.proto.grpc.UserResponseList> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getGetUserWrapperByNameMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * 四、客户端和服务端都以流的方式将消息发送给对方
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.hk.proto.grpc.StreamRequest> biTalk(
        io.grpc.stub.StreamObserver<com.hk.proto.grpc.StreamResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getBiTalkMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class StudentServiceBlockingStub extends io.grpc.stub.AbstractStub<StudentServiceBlockingStub> {
    private StudentServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StudentServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StudentServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *一、 客户端是单一结果，服务端返回单一结果
     * </pre>
     */
    public com.hk.proto.grpc.StudentResponse getRealNameByName(com.hk.proto.grpc.StudentRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetRealNameByNameMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * 二、客户端是单一结果，服务端返回多结果(集合)
     * 注意，这里请求参数不能为int32，即使只有一个参数，也要用 message 定义成对象，这是和 thrift 不同之处
     *    rpc getUserByName2 (int32) returns (stream UserResponse) {}
     * </pre>
     */
    public java.util.Iterator<com.hk.proto.grpc.UserResponse> getUserByName(
        com.hk.proto.grpc.UserRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getGetUserByNameMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class StudentServiceFutureStub extends io.grpc.stub.AbstractStub<StudentServiceFutureStub> {
    private StudentServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private StudentServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StudentServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new StudentServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *一、 客户端是单一结果，服务端返回单一结果
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.hk.proto.grpc.StudentResponse> getRealNameByName(
        com.hk.proto.grpc.StudentRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetRealNameByNameMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_REAL_NAME_BY_NAME = 0;
  private static final int METHODID_GET_USER_BY_NAME = 1;
  private static final int METHODID_GET_USER_WRAPPER_BY_NAME = 2;
  private static final int METHODID_BI_TALK = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final StudentServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(StudentServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_REAL_NAME_BY_NAME:
          serviceImpl.getRealNameByName((com.hk.proto.grpc.StudentRequest) request,
              (io.grpc.stub.StreamObserver<com.hk.proto.grpc.StudentResponse>) responseObserver);
          break;
        case METHODID_GET_USER_BY_NAME:
          serviceImpl.getUserByName((com.hk.proto.grpc.UserRequest) request,
              (io.grpc.stub.StreamObserver<com.hk.proto.grpc.UserResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_USER_WRAPPER_BY_NAME:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.getUserWrapperByName(
              (io.grpc.stub.StreamObserver<com.hk.proto.grpc.UserResponseList>) responseObserver);
        case METHODID_BI_TALK:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.biTalk(
              (io.grpc.stub.StreamObserver<com.hk.proto.grpc.StreamResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class StudentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    StudentServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.hk.proto.grpc.StudentProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("StudentService");
    }
  }

  private static final class StudentServiceFileDescriptorSupplier
      extends StudentServiceBaseDescriptorSupplier {
    StudentServiceFileDescriptorSupplier() {}
  }

  private static final class StudentServiceMethodDescriptorSupplier
      extends StudentServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    StudentServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (StudentServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new StudentServiceFileDescriptorSupplier())
              .addMethod(getGetRealNameByNameMethod())
              .addMethod(getGetUserByNameMethod())
              .addMethod(getGetUserWrapperByNameMethod())
              .addMethod(getBiTalkMethod())
              .build();
        }
      }
    }
    return result;
  }
}
