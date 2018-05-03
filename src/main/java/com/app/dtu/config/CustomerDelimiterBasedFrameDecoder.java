package com.app.dtu.config;

import com.app.dtu.util.DtuUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CustomerDelimiterBasedFrameDecoder extends DelimiterBasedFrameDecoder {
    private static final Logger logger = LoggerFactory.getLogger(CustomerDelimiterBasedFrameDecoder.class);

    public CustomerDelimiterBasedFrameDecoder(int maxFrameLength, ByteBuf delimiter) {
        super(maxFrameLength, delimiter);

    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf buffer) throws Exception {
        return super.decode(ctx, buffer);
    }

    @Override
    protected void decodeLast(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
        super.decodeLast(ctx, buffer, out);
    }

    @Override
    protected void callDecode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) {
        byte[] logBytes = new byte[buffer.readableBytes()];
        buffer.asReadOnly().readBytes(logBytes, 0, logBytes.length);
        // 打印去掉包尾的原始数据
        logger.info("Receiver all message data is [{}]", DtuUtil.bytesToHexString(logBytes));
        super.callDecode(ctx, buffer, out);
    }
}
