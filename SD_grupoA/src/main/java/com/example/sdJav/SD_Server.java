package com.example.sdJav;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.util.Collection;
import java.util.List;


public class SD_Server {
    private int port = 8080;
    private io.grpc.Server server;

    private class GreeterImpl extends GreeterGrpc.GreeterImplBase {
        private final List<Seeder> seeders_list = null;
        //private final JsonArray seeders = new JsonArray();
        private String jsonSeederObject = new String();

        private boolean checkSeeder(Seeder seeder) {
            for (Seeder s : seeders_list) {
                if (s.getStreamName().equals(seeder.getStreamName())) {
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

                Gson gson = new Gson();
                this.seeders_list.add(request);
                jsonSeederObject = gson.toJson(this.seeders_list);
            }
            responseStreamObserver.onNext(response);
            responseStreamObserver.onCompleted();
        }

        public void closureSeeder(Seeder request, StreamObserver<GetSeederResponse> responseStreamObserver) {
            GetSeederResponse response;
            if(checkSeeder(request)){
                this.seeders_list.remove(request);
                Gson gson = new Gson();
                jsonSeederObject = gson.toJson(this.seeders_list);
                response = GetSeederResponse.newBuilder().setMessage(request.getStreamName()+ "closed").build();
            }
            else {
                response = GetSeederResponse.newBuilder().setMessage(request.getStreamName()+ "inexistant").build();
            }
            responseStreamObserver.onNext(response);
            responseStreamObserver.onCompleted();
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
