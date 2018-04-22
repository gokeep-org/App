package com.app.dtu.handlers;

import com.app.dtu.bean.DataMsg;
import com.app.dtu.bean.Message;
import com.app.dtu.config.Const;
import com.app.dtu.parser.ByteUtils;
import com.app.dtu.util.DtuUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 *  处理消息
 */
public class DtuMsgHeaderHandler extends ChannelInboundHandlerAdapter {
    private static final Logger loggger = LoggerFactory.getLogger(DtuServerHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // 打印接受原始数据

        // 转成netty的Bytebuf对象
        ByteBuf result = (ByteBuf) msg;
        if (Objects.isNull(result)){
            loggger.error("Receiver message data is null");
        }
        byte[] logBytes = new byte[result.readableBytes()];
        result.asReadOnly().readBytes(logBytes, 0, logBytes.length);
        // 打印去掉包尾的原始数据
        loggger.info("Receiver message data is [{}]", DtuUtil.bytesToHexString(logBytes));
        // 计算该消息总的数据长度
        int len = result.readableBytes();
        // 判断如果数据长度小于0， 那么不做处理
        if (len < Const.FIXED_LEN){
            return;
        }

        Message message = new Message();

        byte[] headBytes = new byte[16];

        // 先读取帧头，不做处理
        result.readBytes(headBytes, 0, 4);
        /**
         * 用来重置到当前标记的指针位置
         * result.markReaderIndex();
         * result.resetReaderIndex();
         */

            String id;
            result.readBytes(headBytes, 0, 5);
            id = ByteUtils.bytesToHexString(headBytes, 5);
            result.readBytes(headBytes, 0, 2);
            int address = (headBytes[0] & 0xFF) * 256 + (headBytes[1] & 0xFF);

            id += String.format("%06d", address);
            message.setId(id);
            message.setWarnList(result.readUnsignedShort());
            message.setControCmd(result.readUnsignedByte());
            message.setDataLen(result.readUnsignedShort());
            // 这里取出的字节码剩余大小包含自己，如果为4那么会继续走一次，从而读取异常， 剩下的为校验码和帧尾
            while (result.readableBytes() - 3 > 0){
                DataMsg dataMsg = new DataMsg();
                dataMsg.setType(result.readUnsignedByte());
                dataMsg.setLen(result.readUnsignedByte());
                // 这个区是取出的两个
                for (int i =0 ; i < dataMsg.getLen(); i += 2){
                    dataMsg.addData(result.readUnsignedShort());
                }
                message.addDataMsgs(dataMsg);
            }
            // 释放字节码流
            result.release();
            loggger.info("Parse data to message is {}", Objects.isNull(message) ? null : message.toString());
        // 把解析后的消息交给下一个pipline做处理
        ctx.fireChannelRead(message);
    }
}
