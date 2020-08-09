package com.ruoyi.project.server.protocol.serialize;

import com.ruoyi.project.server.protocol.Packet;

/**
 * @author chenli.fnst
 * @date 2020/8/7 11:30
 */
public interface Serializer {

    /**
     * 序列化
     *
     * @param packet packet对象，数据部
     * @return byte[]
     */
    byte[] serialize(Packet packet);
}
