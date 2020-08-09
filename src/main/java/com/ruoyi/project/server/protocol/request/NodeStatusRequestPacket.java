package com.ruoyi.project.server.protocol.request;

import com.ruoyi.project.server.protocol.Packet;
import com.ruoyi.project.server.protocol.command.Command;
import lombok.Data;

/**
 * @author chenli.fnst
 * @date 2020/8/7 15:23
 */
@Data
public class NodeStatusRequestPacket extends Packet {

    private int status;
    private int progress;
    private int currentCaseNum;
    private int totalCaseNum;
    private int result;

    @Override
    public Byte getCommand() {
        return Command.NODE_STATUS_COMMAND;
    }
}
