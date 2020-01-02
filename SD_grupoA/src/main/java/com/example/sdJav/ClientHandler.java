package com.example.sdJav;

import java.io.*;
import java.net.Socket;
import java.util.List;

class ClientHandler extends Thread
    {
        final DataInputStream dis;
        final DataOutputStream dos;
        final Socket s;
        List<File> lof;
        byte[]buf = new byte[4092];
        int n=0;


        // Constructor
        public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos,List<File> lof)
        {
            this.s = s;
            this.dis = dis;
            this.dos = dos;
            this.lof = lof;
        }

        @Override
        public void run()
        {
            String received;
            String toreturn;
            while (true)
            {
                try {

                    for (int i = 0; i < lof.size(); i++) {
                        FileInputStream fis = new FileInputStream(lof.get(i));
                        while ((n = fis.read(buf)) != -1) {
                            dos.write(buf, 0, n);
                            dos.flush();
                        }
                    }

                    // receive the answer from client
                    received = dis.readUTF();

                    if(received.equals("Exit"))
                    {
                        System.out.println("Client " + this.s + " sends exit...");
                        System.out.println("Closing this connection.");
                        this.s.close();
                        System.out.println("Connection closed");
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try
            {
                // closing resources
                this.dis.close();
                this.dos.close();

            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

