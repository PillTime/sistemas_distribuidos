package com.example.sdJav;

import com.google.gson.Gson;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


public class SD_Server {
    private int port = 50051;
    private io.grpc.Server server;
    Gson gson = new Gson();

    private class GreeterImpl extends GreeterGrpc.GreeterImplBase {
        private List<Seeder> seederList = new ArrayList<>();
        private String jsonSeederObject;

        private GreeterImpl() {
        }

        private boolean checkSeeder(Seeder seeder) {
            for (Seeder s : seederList)
                if (Objects.equals(s.getStreamName(), seeder.getStreamName())) {
                    return true;
                }
            return false;
        }

        public void registerSeeder(Seeder request, StreamObserver<GetSeederResponse> responseStreamObserver) {
            GetSeederResponse response;
            if (checkSeeder(request)) {
                response = GetSeederResponse.newBuilder()
                        .setMessage("Seeder ID " + request.getStreamName() + " already exists!").build();
            }
            else {
                Seeder.newBuilder()
                        .setStreamName(request.getStreamName())
                        .setBitrate(request.getBitrate())
                        .setEndPoint(request.getEndPoint()).build();
                response = GetSeederResponse.newBuilder()
                        .setMessage("Seeder ID " + request.getStreamName() + " registry successful!").build();

                seederList.add(request);
                jsonSeederObject = gson.toJson(seederList);
                System.out.println(jsonSeederObject);
            }
            responseStreamObserver.onNext(response);
            responseStreamObserver.onCompleted();

        }

        public void closureSeeder(Seeder request, StreamObserver<GetSeederResponse> responseStreamObserver) {
            GetSeederResponse response;
            if(checkSeeder(request)){
                this.seederList.remove(request);
                jsonSeederObject = gson.toJson(this.seederList);
                response = GetSeederResponse.newBuilder()
                        .setMessage(request.getStreamName()+ "closed").build();
            }
            else {
                response = GetSeederResponse.newBuilder()
                        .setMessage(request.getStreamName()+ "inexistant").build();
            }
            responseStreamObserver.onNext(response);
            responseStreamObserver.onCompleted();
        }

        public void ListSeeders(StreamObserver<ListSeedersResponse> responseStreamObserver) {
            ListSeedersResponse response;

            response = ListSeedersResponse.newBuilder().setSeeders(jsonSeederObject).build();
            responseStreamObserver.onNext(response);
            responseStreamObserver.onCompleted();
        }
    }

    private void start() throws Exception {
        this.server = ServerBuilder.forPort(this.port).addService(new GreeterImpl()).build().start();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("Shutting down server because JVM is shutting down!");
                SD_Server.this.stop();
            }
        });
    }

    private void stop() {
        if (this.server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (this.server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws Exception {
        final SD_Server server = new SD_Server();
        server.start();
        server.blockUntilShutdown();
    }
}
