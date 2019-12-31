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
public final class SendBlockGrpc {

  private SendBlockGrpc() {}

  public static final String SERVICE_NAME = "sdProto.SendBlock";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.sdJav.Block,
      com.example.sdJav.ClientResponse> getSendMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "send",
      requestType = com.example.sdJav.Block.class,
      responseType = com.example.sdJav.ClientResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.sdJav.Block,
      com.example.sdJav.ClientResponse> getSendMethod() {
    io.grpc.MethodDescriptor<com.example.sdJav.Block, com.example.sdJav.ClientResponse> getSendMethod;
    if ((getSendMethod = SendBlockGrpc.getSendMethod) == null) {
      synchronized (SendBlockGrpc.class) {
        if ((getSendMethod = SendBlockGrpc.getSendMethod) == null) {
          SendBlockGrpc.getSendMethod = getSendMethod =
              io.grpc.MethodDescriptor.<com.example.sdJav.Block, com.example.sdJav.ClientResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "send"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.sdJav.Block.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.sdJav.ClientResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SendBlockMethodDescriptorSupplier("send"))
              .build();
        }
      }
    }
    return getSendMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SendBlockStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SendBlockStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SendBlockStub>() {
        @java.lang.Override
        public SendBlockStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SendBlockStub(channel, callOptions);
        }
      };
    return SendBlockStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SendBlockBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SendBlockBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SendBlockBlockingStub>() {
        @java.lang.Override
        public SendBlockBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SendBlockBlockingStub(channel, callOptions);
        }
      };
    return SendBlockBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SendBlockFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SendBlockFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SendBlockFutureStub>() {
        @java.lang.Override
        public SendBlockFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SendBlockFutureStub(channel, callOptions);
        }
      };
    return SendBlockFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class SendBlockImplBase implements io.grpc.BindableService {

    /**
     */
    public void send(com.example.sdJav.Block request,
        io.grpc.stub.StreamObserver<com.example.sdJav.ClientResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSendMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSendMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.sdJav.Block,
                com.example.sdJav.ClientResponse>(
                  this, METHODID_SEND)))
          .build();
    }
  }

  /**
   */
  public static final class SendBlockStub extends io.grpc.stub.AbstractAsyncStub<SendBlockStub> {
    private SendBlockStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SendBlockStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SendBlockStub(channel, callOptions);
    }

    /**
     */
    public void send(com.example.sdJav.Block request,
        io.grpc.stub.StreamObserver<com.example.sdJav.ClientResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class SendBlockBlockingStub extends io.grpc.stub.AbstractBlockingStub<SendBlockBlockingStub> {
    private SendBlockBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SendBlockBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SendBlockBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.sdJav.ClientResponse send(com.example.sdJav.Block request) {
      return blockingUnaryCall(
          getChannel(), getSendMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class SendBlockFutureStub extends io.grpc.stub.AbstractFutureStub<SendBlockFutureStub> {
    private SendBlockFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SendBlockFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SendBlockFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.sdJav.ClientResponse> send(
        com.example.sdJav.Block request) {
      return futureUnaryCall(
          getChannel().newCall(getSendMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SendBlockImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SendBlockImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND:
          serviceImpl.send((com.example.sdJav.Block) request,
              (io.grpc.stub.StreamObserver<com.example.sdJav.ClientResponse>) responseObserver);
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

  private static abstract class SendBlockBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SendBlockBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.sdJav.SDProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SendBlock");
    }
  }

  private static final class SendBlockFileDescriptorSupplier
      extends SendBlockBaseDescriptorSupplier {
    SendBlockFileDescriptorSupplier() {}
  }

  private static final class SendBlockMethodDescriptorSupplier
      extends SendBlockBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SendBlockMethodDescriptorSupplier(String methodName) {
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
      synchronized (SendBlockGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SendBlockFileDescriptorSupplier())
              .addMethod(getSendMethod())
              .build();
        }
      }
    }
    return result;
  }
}
