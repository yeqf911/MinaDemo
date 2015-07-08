package com.yeqianfeng.server;

import com.yeqianfeng.server.factory.MyTextLineCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by yeqf on 7/7/15.
 */
public class MinaServer {

    public static void main(String[] args) {
        try {

            NioSocketAcceptor acceptor = new NioSocketAcceptor();
            acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MyTextLineCodecFactory()));
            acceptor.setHandler(new ServerHandler());
            //acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 4);
            acceptor.bind(new InetSocketAddress(9898));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
