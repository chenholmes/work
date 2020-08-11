package com.ruoyi.project.server.protocol.packet;

import com.ruoyi.project.server.protocol.Packet;
import com.ruoyi.project.server.protocol.command.Command;
import lombok.Data;

import java.util.List;

/**
 * <p>Date: 2020-08-09 21:47.</p>
 *
 * @author chenli
 */
@Data
public class VsgStatusPacket extends Packet {

    private int num;
    private List<String> vsgList;

    @Override
    public Byte getCommand() {
        return Command.VSG_STATUS_COMMAND;
    }
}
