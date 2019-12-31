package com.example.sdJav;

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
    value = "by gRPC proto compiler (version 1.26.0)",
    comments = "Source: sdPro.proto")
public final class RegisterSeederGrpc {

  private RegisterSeederGrpc() {}

  public static final String SERVICE_NAME = "sdProto.RegisterSeeder";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.sdJav.Seeder,
      com.example.sdJav.GetSeederResponse> getRegisterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "register",
      requestType = com.example.sdJav.Seeder.class,
      responseType = com.example.sdJav.GetSeederResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.sdJav.Seeder,
      com.example.sdJav.GetSeederResponse> getRegisterMethod() {
    io.grpc.MethodDescriptor<com.example.sdJav.Seeder, com.example.sdJav.GetSeederResponse> getRegisterMethod;
    if ((getRegisterMethod = RegisterSeederGrpc.getRegisterMethod) == null) {
      synchronized (RegisterSeederGrpc.class) {
        if ((getRegisterMethod = RegisterSeederGrpc.getRegisterMethod) == null) {
          RegisterSeederGrpc.getRegisterMethod = getRegisterMethod =
              io.grpc.MethodDescriptor.<com.example.sdJav.Seeder, com.example.sdJav.GetSeederResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "register"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.sdJav.Seeder.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.sdJav.GetSeederResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RegisterSeederMethodDescriptorSupplier("register"))
              .build();
        }
      }
    }
    return getRegisterMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RegisterSeederStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RegisterSeederStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RegisterSeederStub>() {
        @java.lang.Override
        public RegisterSeederStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RegisterSeederStub(channel, callOptions);
        }
      };
    return RegisterSeederStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RegisterSeederBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RegisterSeederBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RegisterSeederBlockingStub>() {
        @java.lang.Override
        public RegisterSeederBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RegisterSeederBlockingStub(channel, callOptions);
        }
      };
    return RegisterSeederBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RegisterSeederFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RegisterSeederFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RegisterSeederFutureStub>() {
        @java.lang.Override
        public RegisterSeederFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RegisterSeederFutureStub(channel, callOptions);
        }
      };
    return RegisterSeederFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class RegisterSeederImplBase implements io.grpc.BindableService {

    /**
     */
    public void register(com.example.sdJav.Seeder request,
        io.grpc.stub.StreamObserver<com.example.sdJav.GetSeederResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRegisterMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.sdJav.Seeder,
                com.example.sdJav.GetSeederResponse>(
                  this, METHODID_REGISTER)))
          .build();
    }
  }

  /**
   */
  public static final class RegisterSeederStub extends io.grpc.stub.AbstractAsyncStub<RegisterSeederStub> {
    private RegisterSeederStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RegisterSeederStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RegisterSeederStub(channel, callOptions);
    }

    /**
     */
    public void register(com.example.sdJav.Seeder request,
        io.grpc.stub.StreamObserver<com.example.sdJav.GetSeederResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class RegisterSeederBlockingStub extends io.grpc.stub.AbstractBlockingStub<RegisterSeederBlockingStub> {
    private RegisterSeederBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RegisterSeederBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RegisterSeederBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.sdJav.GetSeederResponse register(com.example.sdJav.Seeder request) {
      return blockingUnaryCall(
          getChannel(), getRegisterMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class RegisterSeederFutureStub extends io.grpc.stub.AbstractFutureStub<RegisterSeederFutureStub> {
    private RegisterSeederFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RegisterSeederFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RegisterSeederFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.sdJav.GetSeederResponse> register(
        com.example.sdJav.Seeder request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RegisterSeederImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RegisterSeederImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER:
          serviceImpl.register((com.example.sdJav.Seeder) request,
              (io.grpc.stub.StreamObserver<com.example.sdJav.GetSeederResponse>) responseObserver);
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
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class RegisterSeederBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RegisterSeederBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.sdJav.SDProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RegisterSeeder");
    }
  }

  private static final class RegisterSeederFileDescriptorSupplier
      extends RegisterSeederBaseDescriptorSupplier {
    RegisterSeederFileDescriptorSupplier() {}
  }

  private static final class RegisterSeederMethodDescriptorSupplier
      extends RegisterSeederBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RegisterSeederMethodDescriptorSupplier(String methodName) {
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
      synchronized (RegisterSeederGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RegisterSeederFileDescriptorSupplier())
              .addMethod(getRegisterMethod())
              .build();
        }
      }
    }
    return result;
  }
}
