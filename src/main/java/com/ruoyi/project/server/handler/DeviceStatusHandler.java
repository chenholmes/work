package com.ruoyi.project.server.handler;

import com.ruoyi.project.server.attribute.Attributes;
import com.ruoyi.project.server.protocol.packet.DeviceStatusPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Date: 2020-08-09 21:19.</p>
 *
 * @author chenli
 */
@ChannelHandler.Sharable
public class DeviceStatusHandler extends SimpleChannelInboundHandler<DeviceStatusPacket> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceStatusHandler.class);

    private DeviceStatusHandler(){}

    public static final DeviceStatusHandler INSTANCE = new DeviceStatusHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DeviceStatusPacket deviceStatusPacket) throws Exception {
        String computerName = channelHandlerContext.channel().attr(Attributes.client).get();
        //TODO DB
        channelHandlerContext.channel().writeAndFlush(deviceStatusPacket);
    }
}
