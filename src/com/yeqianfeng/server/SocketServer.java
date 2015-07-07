package com.yeqianfeng.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by yeqf on 7/7/15.
 */
public class SocketServer {

    private BufferedWriter writer = null;
    private BufferedReader reader = null;

    public SocketServer() {
    }

    public static void main(String[] args) {
        SocketServer server = new SocketServer();
        server.startSever();
    }

    public void startSever() {
        ServerSocket serverSocket = null;
        //Socket socket = null;

        try {
            serverSocket = new ServerSocket(9898);
            System.out.println("Server started");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("client " + socket.hashCode() + " connected  ");
                connectionManager(socket);
            }

/*            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        writer.write("heart beats once...\n");
                        writer.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }, 2000, 2000);*/

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //socket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void timeTask(final Socket socket) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedWriter writer = null;
                try {
                    while (true) {
                        Thread.sleep(2000);
                        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        writer.write("123\n");
                        writer.flush();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void connectionManager(final Socket socket) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //timeTask(socket);
                    writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String recieveMessage;
                    while ((recieveMessage = reader.readLine()) != null) {
                        System.out.println(recieveMessage);
                        writer.write("Server reply: " + recieveMessage + "\n");
                        writer.flush();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        reader.close();
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }
}
