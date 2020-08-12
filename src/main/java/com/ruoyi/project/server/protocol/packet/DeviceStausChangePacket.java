package com.ruoyi.project.server.protocol.packet;

import com.ruoyi.project.server.protocol.Packet;
import com.ruoyi.project.server.protocol.command.Command;
import lombok.Data;

@Data
public class DeviceStausChangePacket extends Packet {

    private int status;
    private String deviceName;
    private String vsgName;

    @Override
    public Byte getCommand() {
        return Command.DEVICE_STATUS_CHANGE_COMMAND;
    }
}
