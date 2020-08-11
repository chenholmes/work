package com.ruoyi.project.server.protocol.packet;

import com.ruoyi.project.server.protocol.Packet;
import lombok.Data;

@Data
public class ReturnDataPacket extends Packet {

    private byte command;
    @Override
    public Byte getCommand() {
        return command;
    }
}
