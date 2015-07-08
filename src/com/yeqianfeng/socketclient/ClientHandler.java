package com.yeqianfeng.socketclient;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * Created by yeqf on 7/7/15.
 */
public class ClientHandler extends IoHandlerAdapter {

    public ClientHandler() {
        super();
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("sessionCreated");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("sessionOpened");
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("sessionClosed");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        System.out.println("sessionIdle");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        System.out.println("exceptionCaught");
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String ms = (String) message;
        System.out.println("messageReceived:" + ms);
        //session.write("server reply: " + ms);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        String ms = (String) message;
        System.out.println("messageSent: " + ms);
    }
}
