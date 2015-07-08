package com.yeqianfeng.socketclient;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

/**
 * Created by yeqf on 7/8/15.
 */
public class MinaClient {

    public static void main(String[] args) {
        MinaClient client = new MinaClient();
        client.startClient();
    }

    public void startClient() {
        NioSocketConnector connector = new NioSocketConnector();
        connector.setHandler(new ClientHandler());
        connector.getFilterChain().addLast("coderc", new ProtocolCodecFilter(new TextLineCodecFactory()));
        ConnectFuture future = connector.connect(new InetSocketAddress("127.0.0.1", 9898));
        future.awaitUninterruptibly();
        IoSession session = future.getSession();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String message;
        try {
            while (!(message = reader.readLine()).equals("bye")) {
                session.write(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
