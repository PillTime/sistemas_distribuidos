package com.example.sdJav;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SD_Seeder {
    private static final Logger logger = Logger.getLogger(SD_Seeder.class.getName());

    private final ManagedChannel channel;
    private final GreeterGrpc.GreeterBlockingStub blockingStub;

    private Seeder active_seeder;

    /** Construct client connecting to HelloWorld server at {@code host:port}. */
    public SD_Seeder(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }


    /** Say hello to server. */
    public void greet(String name) {
        try {
            logger.info("Will try to greet " + name + " ...");
            HelloRequest request = HelloRequest.newBuilder()
                    .setName(name).build();
            HelloResponse response = blockingStub.sayHello(request);
            logger.info("Greeting: " + response.getMessage());
        } catch (RuntimeException e) {
            logger.log(Level.WARNING, "RPC failed", e);
            return;
        }
    }

    public void init_seeder(String stream_name){
        try {
            EndPoint endp = EndPoint.newBuilder()
                    .setIp("localhost")
                    .setPort(8080)
                    .setTransport("tcp").build();
            Seeder active_seeder = Seeder.newBuilder()
                    .setBitrate(124)
                    .setStreamName(stream_name)
                    .setVideoSize(20)
                    .setEndPoint(endp).build();
            GetSeederResponse res = blockingStub.registerSeeder(active_seeder);
            logger.info(res.getMessage());
        } catch (RuntimeException e){
            logger.log(Level.WARNING, "RPC failed", e);
            return;
        }

        //Need to do the initial download from the grpc server
    }

    public void close_seeder(){
        try {
            GetSeederResponse res = blockingStub.closureSeeder(active_seeder);
            logger.info(res.getMessage());
        } catch (RuntimeException e){
            logger.log(Level.WARNING, "RPC failed", e);
            return;
        }
    }

    //the function that will get the file from the server
    public void getFile(String stream_name){

    }

    //the function that waits from a stream request
    public void streamRequest(){

    }
    /**
     * Greet server. If provided, the first element of {@code args} is the name to use in the
     * greeting.
     */
    public static void main(String[] args) throws Exception {
        SD_Seeder client = new SD_Seeder("localhost", 50051);

        try {
            /* Access a service running on the local machine on port 50051 */
            String user = "world";
            if (args.length > 0) {
                user = args[0]; /* Use the arg as the name to greet if provided */
            }
            client.greet(user);
        } finally {
            client.shutdown();
        }
    }
}

