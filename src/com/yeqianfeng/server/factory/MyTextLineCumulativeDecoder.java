package com.yeqianfeng.server.factory;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * Created by yeqf on 7/8/15.
 */
public class MyTextLineCumulativeDecoder extends CumulativeProtocolDecoder {
    /**
     *
     * @param ioSession 会话
     * @param in shu ru liu (yong yu jie ma de)
     * @param out shu chu liu
     * @return boolean
     * @throws Exception
     * 避免数据丢失 Decoder xie fa.
     */
    @Override
    protected boolean doDecode(IoSession ioSession, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
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
                return true;
            }
        }
        in.position(startPosition);
        return false;
    }
}
