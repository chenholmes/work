package com.ruoyi.project.server.protocol.packet;

import com.ruoyi.project.server.protocol.Packet;
import com.ruoyi.project.server.protocol.command.Command;
import lombok.Data;

import java.util.List;

@Data
public class CaseSendPacket extends Packet {
    private int num;
    private List<String> casePath;

    @Override
    public Byte getCommand() {
        return Command.CASE_SEND_COMMAND;
    }
}
