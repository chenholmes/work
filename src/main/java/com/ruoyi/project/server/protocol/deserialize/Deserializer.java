package com.ruoyi.project.server.protocol.deserialize;

import com.ruoyi.project.server.protocol.Packet;

/**
 * @author chenli.fnst
 * @date 2020/8/5 17:26
 */
public interface Deserializer {

    /**
     * 反序列化
     *
     * @param data 数据报
     * @return Packet对象, 数据部
     */
    Packet deserialize(byte[] data);
}
