package com.app.dtu.handlers;

import com.app.dtu.bean.BasicProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class SmartProtocolEncoder extends MessageToByteEncoder<BasicProtocol> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, BasicProtocol msg, ByteBuf out){
        // 写入消息SmartCar的具体内容
        // 1.写入消息的开头的信息标志(int类型)
        out.writeInt(msg.getHead_data());
        // 2.写入消息的长度(int 类型)
        out.writeInt(msg.getContentLength());
        // 3.写入消息的内容(byte[]类型)
        out.writeBytes(msg.getContent());
    }
}
