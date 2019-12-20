package org.potholes.api;

public enum ResponseStatus {

    TOKEN_INVALID(-1, "Token过期"),
    ERROR(0, "失败"),
    SUCCESS(1, "成功"),

    METHOD_NOT_SUPPORTED(7777, "请求类型不支持"),
    PARAM_ERROR(8888, "参数错误"),
    SYSTEM_ERROR(9999, "系统错误");

    private final int status;
    private final String msg;

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    private ResponseStatus(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

}
