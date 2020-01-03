package com.example.sdJav;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.StorageOptions;
import io.grpc.Context;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.cloud.storage.Storage;


public class SD_Seeder {
    private static final Logger logger = Logger.getLogger(SD_Seeder.class.getName());

    private final ManagedChannel channel;
    private final GreeterGrpc.GreeterBlockingStub blockingStub;

    private Seeder active_seeder;
    public static List<File> listOfFiles = new ArrayList<>();

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


    public static String find_ip(){
        String systemipaddress = "";
        try
        {
            URL url_name = new URL("http://bot.whatismyipaddress.com");

            BufferedReader sc =
                    new BufferedReader(new InputStreamReader(url_name.openStream()));

            // reads system IPAddress
            systemipaddress = sc.readLine().trim();
        }
        catch (Exception e)
        {
            systemipaddress = "Cannot Execute Properly";
        }
        System.out.println("Public IP Address: " + systemipaddress +"\n");
        return systemipaddress;
    }

    public void init_seeder(String stream_name){
        try {
            EndPoint endp = EndPoint.newBuilder()
                    .setIp(find_ip())
                    .setPort(50051)
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

    public static void splitFile(File f) throws IOException {
        //listOfFiles.clear();
        int partCounter = 1;//I like to name parts from 001, 002, 003, ...
        //you can change it to 0 if you want 000, 001, ...

        int sizeOfFiles = 1024 * 1024;// 1MB
        byte[] buffer = new byte[sizeOfFiles];

        String fileName = f.getName();

        //try-with-resources to ensure closing stream
        try (FileInputStream fis = new FileInputStream(f);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            int bytesAmount = 0;
            while ((bytesAmount = bis.read(buffer)) > 0) {
                //write each chunk of data into separate file with different number in name
                String filePartName = String.format("%s.%03d", fileName, partCounter++);

                File newFile = new File(f.getParent(), filePartName);
                listOfFiles.add(newFile);
            }
        }
    }



    //the function that will get the file from the server
    public static void getFile(String stream_name) throws IOException {
        // The name of the bucket to access
        String bucketName = "videos_sd";

        // The name of the remote file to download
        String srcFilename = stream_name+".mp4";

        // The path to which the file should be downloaded
        Path destFilePath = Paths.get(System.getProperty("user.dir"));
        System.out.println(destFilePath);

        // Instantiate a Google Cloud Storage client
        Storage storage = StorageOptions.getDefaultInstance().getService();

        // Get specific file from specified bucket
        Blob blob = storage.get(BlobId.of(bucketName, srcFilename));

        // Download file to specified path
        blob.downloadTo(destFilePath);

        File f = new File(destFilePath+stream_name+".mp4");
        splitFile(f);
    }

    /**
     * Greet server. If provided, the first element of {@code args} is the name to use in the
     * greeting.
     */
    public static void main(String[] args) throws Exception {
        SD_Seeder client = new SD_Seeder("localhost", 50051);
        String file_name;

        try {
            /* Access a service running on the local machine on port 50051 */
            file_name = "world";
            if (args.length > 0) {
                file_name = args[0]; /* Use the arg as the name to greet if provided */
            }
            //client.greet(file_name);
            client.init_seeder(file_name);
        } finally {
            client.shutdown();
            System.out.println("Client Shutdown");
        }


        //----------------------
        getFile(file_name);

        ServerSocket ss = new ServerSocket(8080);

        // running infinite loop for getting
        // client request
        while (true)
        {
            Socket s = null;
            try
            {
                // socket object to receive incoming client requests
                s = ss.accept();

                System.out.println("A new client is connected : " + s);

                // obtaining input and out streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                FileInputStream fis = new FileInputStream(file_name);

                System.out.println("Assigning new thread for this client");

                // create a new thread object
                Thread t = new ClientHandler(s, dis, dos,listOfFiles);

                // Invoking the start() method
                t.start();

            }
            catch (Exception e){
                s.close();
                client = new SD_Seeder("localhost",50051);
                client.close_seeder();
                client.shutdown();
                System.out.println("Client Shutdown");
                e.printStackTrace();
            }
            finally {
                client = new SD_Seeder("localhost",50051);
                client.close_seeder();
                client.shutdown();
                System.out.println("Client Shutdown");
            }
        }

    }
}

