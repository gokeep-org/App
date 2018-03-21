package com.app.dtu.handlers;

import com.app.dtu.bean.DataMsg;
import com.app.dtu.bean.Header;
import com.app.dtu.bean.Message;
import com.app.dtu.config.Const;
import com.app.dtu.parser.ByteUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 *  处理消息
 */
public class DtuMsgHeaderHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // 转成netty的Bytebuf对象
        ByteBuf result = (ByteBuf) msg;
        // 计算该消息总的数据长度
        int len = result.readableBytes();
        // 判断如果数据长度小于0， 那么不做处理
        if (len < Const.FIXED_LEN){
            return;
        }

        Message message = new Message();

//        byte[] stringToByte();
        byte[] headBytes = new byte[16];


        result.readBytes(headBytes, 0, 4);


//
//        result.markReaderIndex();
//        0x5A,0x13,0x56,0x78
        String id ;
        if(Header.compare(headBytes)){
            result.readBytes(headBytes, 0, 5);
            id = ByteUtils.bytesToHexString(headBytes, 5);
            result.readBytes(headBytes, 0, 2);
            int address = (headBytes[0] & 0xFF) * 256 + (headBytes[1] & 0xFF);
//            result.resetReaderIndex();
            id += String.format("%06d", address);
            message.setId(id);
            message.setWarnList(result.readUnsignedShort());
            message.setControCmd(result.readUnsignedByte());
            message.setDataLen(result.readUnsignedShort());

            while (result.readableBytes() - 4 > 0){
                DataMsg dataMsg = new DataMsg();
                dataMsg.setType(result.readUnsignedByte());
                dataMsg.setLen(result.readUnsignedByte());
                for (int i =0 ; i < dataMsg.getLen(); i++){
                    dataMsg.addData(result.readUnsignedShort());
                }
                message.addDataMsgs(dataMsg);
            }
            // TODO: 执行下面的
        }else {
            return;
        }
        ctx.fireChannelRead(message);




        // msg中存储的是ByteBuf类型的数据，把数据读取到byte[]中

        /**
         * 去除消息头
         */
//        result.readBytes(result1);
//        result.readBytes(result1, 0, 2);

    }
}
