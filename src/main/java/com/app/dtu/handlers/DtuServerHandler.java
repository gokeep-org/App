package com.app.dtu.handlers;

import com.app.dtu.bean.Message;
import com.app.dtu.bean.model.MonitorManagerDevice;
import com.app.dtu.service.DataService;
import com.app.util.ApplicationContextHolder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

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
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        logger.info("Netty socket server start process socket message");
        Message result = (Message) msg;

        // 实现数据解析适配器
        MonitorManagerDevice message = new MonitorManagerDevice();
//        MonitorManagerDeviceV2 device = (MonitorManagerDeviceV2) message.parseEntity();/
        DataService dataService = (DataService) ApplicationContextHolder.getContext().getBean("dataServiceImpl");
       message.setCreateDate(new Date().getTime());
       message.setDeviceId(1L);
       message.setWarnDesc("oK");
       message.setWarnList(1);
       message.setId(2L);
        dataService.save(message);


//        byte[] result1 = new byte[result.readableBytes()];
//        /**
//         * TODO: 字节码解析处理
//         */
//
//        /**
//         * TODO: 释放资源
//         */
//        // msg中存储的是ByteBuf类型的数据，把数据读取到byte[]中
//        result.readBytes(result1);
//        String resultStr = new String(result1);
//        System.out.println("Client said:" + resultStr);
//        // 释放资源，这行很关键
//        result.release();
//        String response = "I am ok!";
//        // 在当前场景下，发送的数据必须转换成ByteBuf数组
//        ByteBuf encoded = ctx.alloc().buffer(4 * response.length());
//        encoded.writeBytes(response.getBytes());
//        ctx.write(encoded);
        ctx.fireChannelRead(msg);
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
