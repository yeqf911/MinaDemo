package com.yeqianfeng.server.factory;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * Created by yeqf on 7/8/15.
 */
public class MyProtocolDecoder implements ProtocolDecoder {
    @Override
    public void decode(IoSession ioSession, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        int startPosition = in.position();

        while (in.hasRemaining()) {
            byte b = in.get();
            if (b == '\n') {
                int currentPosition = in.position();
                int limit = in.limit();
                in.position(startPosition);
                in.limit(currentPosition);
                IoBuffer buffer = in.slice();
                byte[] dest = new byte[buffer.limit()];
                buffer.get(dest);
                String message = new String(dest);
                out.write(message);
                in.position(currentPosition);
                in.limit(limit);
            }
        }
    }

    @Override
    public void finishDecode(IoSession ioSession, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {

    }

    @Override
    public void dispose(IoSession ioSession) throws Exception {

    }
}
