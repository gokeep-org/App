package com.app.dtu;

import com.app.dtu.config.DtuConfig;
import com.app.dtu.config.DtuServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;


/***
 * 这里需要实现两种共构建方法
 * 1：使用spring注入的方式在所有的bean加载完毕的时候启动start方法，这里专门实现了NettyServer的接口
 * 2：使用实例化的方法构建，用于非web应用或者单元测试中的方式实例化
 * 3: 实现ApplicationContextAware接口，实现setApplicationContext方法来实现
 */

public class NettyServer  implements com.app.dtu.ServerBootstrap, ApplicationContextAware {
    private static ApplicationContext applicationContext;
    private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    /**
     * 这里的两个对象是单例实现的，不被任何类和方法调用
     */
    private EventLoopGroup bossGroup = new NioEventLoopGroup();
    private EventLoopGroup workerGroup = new NioEventLoopGroup();

    /**
     * 当程序启动容器加载完毕启动该socket服务
     * 当前主线程出现异常退出的时候，应该关闭掉所有的资源
     */
    @PostConstruct
    @Override
    public void start() {
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new DtuServerInitializer())
                    .option(ChannelOption.SO_BACKLOG, DtuConfig.SOCKET_SERVER_SO_BACKLOG)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture future = bootstrap.bind(DtuConfig.SOCKET_SERVER_PORT).sync();
            if (DtuConfig.IS_ENABLE_SOCKET_SERVER){
                future.channel().closeFuture().sync();
            }
            logger.info("Start netty socket server is success");
        } catch (InterruptedException e) {
            logger.error("Start netty socket server is fail, {}", e.getMessage());
        } finally {
            stop();
        }
    }

    @Override
    public void stop() {
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
        logger.info("Netty socket close server is success");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;
        start();
    }
}