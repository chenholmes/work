package com.ruoyi.project.atx.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * atx_node
 * @author 
 */
@Data
public class AtxNodeGenerate implements Serializable {
    /**
     * 设置主键自增,台架ID
     */
    private Integer id;

    /**
     * IP地址
     */
    private String ip;

    /**
     * MAC地址
     */
    private String mac;

    /**
     * 台架所在计算机名称
     */
    private String computerName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}