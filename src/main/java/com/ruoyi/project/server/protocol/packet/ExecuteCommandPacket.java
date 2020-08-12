package com.ruoyi.project.server.protocol.packet;

import com.ruoyi.project.server.protocol.Packet;
import com.ruoyi.project.server.protocol.command.Command;
import lombok.Data;

@Data
public class ExecuteCommandPacket extends Packet {

    private int exeCommand;
    @Override
    public Byte getCommand() {
        return Command.EXECUTE_COMMAND;
    }
}
