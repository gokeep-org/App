package com.app.dtu.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class DtuMsgHeaderHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf result = (ByteBuf) msg;
        byte[] result1 = new byte[result.readableBytes()];
        // msg中存储的是ByteBuf类型的数据，把数据读取到byte[]中
        result.readBytes(result1);
        ctx.fireChannelRead(msg);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
