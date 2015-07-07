package com.yeqianfeng.socketclient;

import sun.swing.StringUIClientPropertyKey;

import java.io.*;
import java.net.Socket;

/**
 * Created by yeqf on 7/7/15.
 */
public class SocketClient {

    public static void main(String[] agrs) {

        SocketClient client = new SocketClient();
        client.start();
    }

    public void start() {
        BufferedReader bufferedReader = null;
        BufferedReader reader = null;
        String messageContent;
        BufferedWriter writer = null;
        Socket socket = null;

        try {

            socket = new Socket("127.0.0.1", 9898);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            startResponseListener(reader);
            while (!(messageContent = bufferedReader.readLine()).equals("bye")) {
                //System.out.println(messageContent);
                writer.write("client " + socket.hashCode() + ": " + messageContent + "\n");
                writer.flush();
  /*              String response = reader.readLine();
                System.out.println(response + "\n");*/
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                bufferedReader.close();
                writer.close();
                reader.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void startResponseListener(BufferedReader reader) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String response;
                try {
                    while ((response = reader.readLine()) != null) {
                        System.out.println(response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
