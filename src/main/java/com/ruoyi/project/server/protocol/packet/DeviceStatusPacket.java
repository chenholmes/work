package com.ruoyi.project.server.protocol.packet;

import com.ruoyi.project.server.protocol.Packet;
import com.ruoyi.project.server.protocol.command.Command;
import lombok.Data;

import java.util.List;

@Data
public class DeviceStatusPacket extends Packet {

    private int num;

    private List<Device> deviceList;

    @Override
    public Byte getCommand() {
        return Command.DEVICE_STATUS_COMMAND;
    }
}
