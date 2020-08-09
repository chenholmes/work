package com.ruoyi.project.server.handler;

import com.ruoyi.project.server.attribute.Attributes;
import com.ruoyi.project.server.protocol.request.NodeStatusRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenli.fnst
 * @date 2020/8/7 15:53
 */
public class NodeStatusHandler extends SimpleChannelInboundHandler<NodeStatusRequestPacket> {

    private static final Logger LOGGER = LoggerFactory.getLogger(NodeStatusHandler.class);

    public static final NodeStatusHandler INSTANCE = new NodeStatusHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, NodeStatusRequestPacket nodeStatusRequestPacket) throws Exception {
        String computerName = channelHandlerContext.channel().attr(Attributes.client).get();
        //TODO DB

        channelHandlerContext.channel().writeAndFlush(nodeStatusRequestPacket);
    }
}
