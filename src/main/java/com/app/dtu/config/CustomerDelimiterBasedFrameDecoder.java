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
        byte[] logBytes = new byte[buffer.readableBytes()];
        buffer.asReadOnly().readBytes(logBytes, 0, logBytes.length);
        // 打印去掉包尾的原始数据
        logger.info("Receiver decode message data is [{}]", DtuUtil.bytesToHexString(logBytes));
        logger.info("bate decode string builer lenght is {}", logBytes.length);
        return super.decode(ctx, buffer);
    }

    @Override
    protected void decodeLast(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
        byte[] logBytes = new byte[buffer.readableBytes()];
        buffer.asReadOnly().readBytes(logBytes, 0, logBytes.length);
        // 打印去掉包尾的原始数据
        logger.info("Receiver decodeLast message data is [{}]", DtuUtil.bytesToHexString(logBytes));
        logger.info("bate decodeLast string builer lenght is {}", logBytes.length);

        super.decodeLast(ctx, buffer, out);
    }

    @Override
    protected void callDecode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) {
        byte[] logBytes = new byte[buffer.readableBytes()];
        buffer.asReadOnly().readBytes(logBytes, 0, logBytes.length);
        // 打印去掉包尾的原始数据
        logger.info("Receiver all message data is [{}]", DtuUtil.bytesToHexString(logBytes));
        logger.info("bate callDecode string builer lenght is {}", logBytes.length);
        super.callDecode(ctx, buffer, out);
    }
}
