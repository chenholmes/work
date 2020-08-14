package com.ruoyi.project.server.constants;

public enum ReturnCodeEnum {

    SUCCESS(0, "命令正确处理"),
    ERROR(1, "命令错误处理"),
    PARAM_ERROR(2, "命令参数错误");

    private int code;
    private String msg;

    ReturnCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static ReturnCodeEnum getEnum(int code) {
        for(ReturnCodeEnum rce : ReturnCodeEnum.values()) {
            if(rce.getCode() == code) {
                return rce;
            }
        }
        return null;
    }
}
