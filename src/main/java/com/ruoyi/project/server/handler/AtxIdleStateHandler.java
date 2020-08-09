package com.ruoyi.project.server.handler;

import com.ruoyi.project.server.NettySocketHandler;
import com.ruoyi.project.server.attribute.Attributes;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 心跳
 * @author chenli.fnst
 * @date 2020/8/6 10:19
 */
public class AtxIdleStateHandler extends IdleStateHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(AtxIdleStateHandler.class);
    private static final int READER_IDLE_TIME = 15;

    public AtxIdleStateHandler() {
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) {
        LOGGER.warn(READER_IDLE_TIME + "秒内未读到数据，关闭连接");
        ctx.channel().close();
        String client = ctx.channel().attr(Attributes.client).get();
        NettySocketHandler.remove(client);
    }
}
