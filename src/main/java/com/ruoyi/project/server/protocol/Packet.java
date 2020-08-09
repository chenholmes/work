package com.ruoyi.project.server.protocol;

import lombok.Data;

/**
 * @author chenli.fnst
 * @date 2020/8/5 13:13
 */
@Data
public abstract class Packet {

    private int result = 0;

    public abstract Byte getCommand();
}
