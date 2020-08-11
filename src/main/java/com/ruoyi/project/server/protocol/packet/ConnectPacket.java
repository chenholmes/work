package com.ruoyi.project.server.protocol.packet;

import com.ruoyi.project.server.protocol.Packet;
import com.ruoyi.project.server.protocol.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author chenli.fnst
 * @date 2020/8/5 17:19
 */
@Data
@AllArgsConstructor
public class ConnectPacket extends Packet {

    private String ip;
    private String mac;
    private String computerName;
    @Override
    public Byte getCommand() {
        return Command.CONNECT_COMMAND;
    }
}
