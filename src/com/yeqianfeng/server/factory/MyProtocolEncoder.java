package com.yeqianfeng.server.factory;

import com.sun.org.apache.xml.internal.serialize.XHTMLSerializer;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.beans.Encoder;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/**
 * Created by yeqf on 7/8/15.
 */
public class MyProtocolEncoder implements ProtocolEncoder {
    @Override
    public void encode(IoSession ioSession, Object message, ProtocolEncoderOutput out) throws Exception {

        CharsetEncoder encoder = (CharsetEncoder) ioSession.getAttribute("encoder");
        if (encoder == null) {
            encoder = Charset.defaultCharset().newEncoder();
            ioSession.setAttribute(encoder);
        }
        String ms = message == null ? "" : message.toString();
        IoBuffer ioBuffer = IoBuffer.allocate(ms.length());
        ioBuffer.setAutoExpand(true);
        ioBuffer.putString(ms, encoder);
        ioBuffer.flip();
        out.write(ioBuffer);
    }

    @Override
    public void dispose(IoSession ioSession) throws Exception {

    }
}
