package com.ruoyi.common.model;

import java.util.List;

/**
 * @author wangsr.fnst
 */
public class VsgStatusOrder {
    Byte vsgNum;
    List<String> vsgInfoList;

    public Byte getVsgNum() {
        return vsgNum;
    }

    public void setVsgNum(Byte vsgNum) {
        this.vsgNum = vsgNum;
    }

    public List<String> getVsgInfoList() {
        return vsgInfoList;
    }

    public void setVsgInfoList(List<String> vsgInfoList) {
        this.vsgInfoList = vsgInfoList;
    }
}
