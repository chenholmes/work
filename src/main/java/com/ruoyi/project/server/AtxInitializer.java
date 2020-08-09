package com.ruoyi.project.server;

import com.ruoyi.project.server.handler.AtxIdleStateHandler;
import com.ruoyi.project.server.handler.AtxServiceHandler;
import com.ruoyi.project.server.handler.ConnectHandler;
import com.ruoyi.project.server.handler.PacketCodecHandler;
import com.ruoyi.project.server.protocol.Spliter;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

/**
 * @author chenli.fnst
 * @date 2020/8/5 10:04
 */
public class AtxInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline()
//                .addLast(new AtxIdleStateHandler())
                .addLast(new Spliter())
                .addLast(PacketCodecHandler.INSTANCE)
                .addLast(ConnectHandler.INSTANCE)
                .addLast(AtxServiceHandler.INSTANCE);
    }
}
