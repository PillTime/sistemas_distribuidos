package com.example.sdJav;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.util.Collection;


public class SD_Server {
    private int port = 8080;
    private io.grpc.Server server;

    private class GreeterImpl extends GreeterGrpc.GreeterImplBase {
        private final Collection<Seeder> seeders_list = null;
        private final JsonArray seeders = new JsonArray();

        private boolean checkSeeder(Seeder seeder) {
            for (Seeder s : seeders_list) {
                if (s.getStreamName() == seeder.getStreamName()) {
                    return true;
                }
            }
            return false;
        }

        public void registerSeeder(Seeder request, StreamObserver<GetSeederResponse> responseStreamObserver) {
            GetSeederResponse response;
            if (checkSeeder(request)) {
                response = GetSeederResponse.newBuilder().setMessage("Seeder ID " + request.getStreamName() + " already exists!").build();
            }
            else {
                Seeder.newBuilder().setStreamName(request.getStreamName()).setBitrate(request.getBitrate()).setEndPoint(request.getEndPoint()).build();
                response = GetSeederResponse.newBuilder().setMessage("Seeder ID " + request.getStreamName() + " registry successful!").build();
                JsonObject object = new JsonObject();
                object.addProperty("Stream_Name", request.getStreamName());
                object.addProperty("Bitrate", request.getBitrate());
                object.addProperty("Video_Size", request.getVideoSize());
                this.seeders.add(object);
                this.seeders_list.add(request);
            }
            responseStreamObserver.onNext(response);
            responseStreamObserver.onCompleted();
        }

        public void closureSeeder(Seeder request, StreamObserver<GetSeederResponse> responseStreamObserver) {
            if (checkSeeder(request)) {
                this.seeders_list.remove(request);
            }
        }
    }

    private void start() throws Exception {
        Collection<Seeder> seeders;
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
