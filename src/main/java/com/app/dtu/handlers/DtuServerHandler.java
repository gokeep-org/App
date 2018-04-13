package com.app.dtu.handlers;

import com.app.dtu.bean.Message;
import com.app.dtu.bean.model.DeviceDataDeal;
import com.app.dtu.config.DtuConfig;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/****************************************
 * Copyright (c) xuning.
 * 尊重版权，禁止抄袭!
 * 如有违反，必将追究其法律责任.
 * @Auther is xuning on 2017/5/14.
 ****************************************/
public class DtuServerHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(DtuServerHandler.class);

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 加入\n");
        }
        channels.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 离开\n");
        }
        channels.remove(incoming);
        logger.info("Netty socket server remove channel success, {}", incoming.remoteAddress());
    }


    /**
     * 从channel中读取数据，进行处理
     * 这里是关闭之前的操作最后业务处理操作应该，需要处理
     * 这里实际是封装成对象进行操作，传递给下一个pipline进行入库操作
     * 这里不再关心数据是如何存储的，只需要写设备的存储业务逻辑即可
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        logger.info("Netty socket server start process socket message");
        Message message = (Message) msg;
        DeviceDataDeal deviceDataDeal = message.getDevice();
        boolean executeResult = Objects.isNull(deviceDataDeal) ? false : deviceDataDeal.execute();
        if (executeResult)
            logger.info("Add device data result is {}", executeResult);
        else
            logger.error("Add device data result is {}", executeResult);
        if (!DtuConfig.ENABLE_KEEP_ALIVE_CONNECTION) {
            ctx.fireChannelRead(msg);
        }
    }


    /**
     * 如果处理出现异常关闭连接，防止资源的无效暂用
     *
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        Channel incoming = ctx.channel();
        logger.error("Listen socket connection {} exception, {}", incoming.remoteAddress(), cause.getMessage());
        ctx.close();
    }
}
