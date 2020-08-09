package com.ruoyi.common.model;

import java.util.Arrays;

/**
 * @author wangsr.fnst
 */
public class NodeStatusOrder {
    Byte status;
    Byte progress;
    byte[] reserve = new byte[2];

    public Byte getStatus() {
        return status;
    }

    /**
     * 参数1:1BYTE
     * 0:初始状态
     * 1：执行
     * 2：暂停
     * @param status
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getProgress() {
        return progress;
    }

    /**
     * 参数2:1BYTE
     * 0-100:测试进度
     * @param progress
     */
    public void setProgress(Byte progress) {
        this.progress = progress;
    }

    public byte[] getReserve() {
        return reserve;
    }

    /**
     * 参数3:2BYTE
     * @param reserve
     */
    public void setReserve(byte[] reserve) {
        this.reserve = reserve;
    }

    @Override
    public String toString() {
        return "NodeStatusOrder{" +
                "status=" + status +
                ", progress=" + progress +
                ", reserve=" + Arrays.toString(reserve) +
                '}';
    }
}
