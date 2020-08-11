package com.ruoyi.project.server.handler;

import com.ruoyi.project.server.protocol.Packet;
import com.ruoyi.project.server.protocol.command.Command;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * 通信命令业务处理
 * @author chenli.fnst
 * @date 2020/8/7 16:13
 */
@ChannelHandler.Sharable
public class AtxServiceHandler extends SimpleChannelInboundHandler<Packet> {

    public static final AtxServiceHandler INSTANCE = new AtxServiceHandler();

    private Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

    private AtxServiceHandler() {
        handlerMap = new HashMap<>();
        handlerMap.put(Command.NODE_STATUS_COMMAND,NodeStatusHandler.INSTANCE);
        handlerMap.put(Command.DEVICE_STATUS_COMMAND,DeviceStatusHandler.INSTANCE);
        handlerMap.put(Command.VSG_STATUS_COMMAND,VsgStatusHandler.INSTANCE);
        handlerMap.put(Command.DEVICE_STATUS_CHANGE_COMMAND,ReturnDataHandler.INSTANCE);
        handlerMap.put(Command.CASE_SEND_COMMAND,ReturnDataHandler.INSTANCE);
        handlerMap.put(Command.CASE_CLEAR_COMMAND,ReturnDataHandler.INSTANCE);
        handlerMap.put(Command.EXECUTE_COMMAND,ReturnDataHandler.INSTANCE);
    }
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception {
        handlerMap.get(packet.getCommand()).channelRead(channelHandlerContext, packet);
    }
}
