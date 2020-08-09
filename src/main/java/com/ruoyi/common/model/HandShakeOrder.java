package com.ruoyi.common.model;

/**
 * 命令ID: 0x01
 * 握手命令
 *
 * @author wangsr.fnst
 */
public class HandShakeOrder {
    /**
     * ip地址'\0'结尾
     */
    String ipString;
    /**
     * mac地址'\0'结尾
     */
    String macString;
    /**
     * 计算机名称'\0'结尾
     */
    String comNameString;

    /**
     *
     * @param ipString
     * @param macString
     * @param comNameString
     */
    public HandShakeOrder(String ipString, String macString, String comNameString) {
        this.ipString = ipString;
        this.macString = macString;
        this.comNameString = comNameString;
    }

    /**
     * @return the ipString
     */
    public String getIpString() {
        return ipString;
    }

    /**
     * @param ipString the ipString to set
     */
    public void setIpString(String ipString) {
        this.ipString = ipString;
    }

    /**
     * @return the macString
     */
    public String getMacString() {
        return macString;
    }

    /**
     * @param macString the macString to set
     */
    public void setMacString(String macString) {
        this.macString = macString;
    }

    /**
     * @return the comNameString
     */
    public String getComNameString() {
        return comNameString;
    }

    /**
     * @param comNameString the comNameString to set
     */
    public void setComNameString(String comNameString) {
        this.comNameString = comNameString;
    }

    @Override
    public String toString() {
        return "HandShakeOrder [ipString=" + ipString + ", macString=" + macString + ", comNameString=" + comNameString
                + "]";
    }


}
