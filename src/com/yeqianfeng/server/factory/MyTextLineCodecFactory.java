package com.yeqianfeng.server.factory;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * Created by yeqf on 7/8/15.
 */
public class MyTextLineCodecFactory implements ProtocolCodecFactory {

    private MyProtocolDecoder mDecoder;
    private MyProtocolEncoder mEncode;
    private MyTextLineCumulativeDecoder decoder;

    public MyTextLineCodecFactory() {
        mDecoder = new MyProtocolDecoder();
        mEncode = new MyProtocolEncoder();
        decoder = new MyTextLineCumulativeDecoder();
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return mEncode;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return decoder;
    }
}

