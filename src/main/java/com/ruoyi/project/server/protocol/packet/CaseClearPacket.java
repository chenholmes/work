package com.ruoyi.project.server.protocol.packet;

import com.ruoyi.project.server.protocol.Packet;
import com.ruoyi.project.server.protocol.command.Command;
import lombok.Data;

import java.util.List;

@Data
public class CaseClearPacket extends Packet {

    private int num;
    private List<String> casePath;

    @Override
    public Byte getCommand() {
        return Command.CASE_CLEAR_COMMAND;
    }
}
