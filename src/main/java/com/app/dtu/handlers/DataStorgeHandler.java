package com.app.dtu.handlers;

import com.app.dtu.config.DtuConfig;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


/**
 * 数据存储处理Handler
 * 这里存储的介质是mysql，通过mysqlbatis调用
 * 使用简单工厂方法调用
 */
public class DataStorgeHandler extends ChannelInboundHandlerAdapter{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // TODO: 获取设备类型，或者唯一性，唯一性表示设备类型的属性
        // TODO: 通过工厂方法，获取数据操作对象
        // 通过篇日志决定是否要进行长连接，否的话就继续进行下一个pipline关闭socket连接
        if (!DtuConfig.ENABLE_KEEP_ALIVE_CONNECTION) {
            ctx.fireChannelRead(msg);
        }
    }
}
