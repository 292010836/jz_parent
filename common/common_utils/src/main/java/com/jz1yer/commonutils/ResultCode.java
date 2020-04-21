package com.jz1yer.commonutils;

/**
 * 公共的返回码
 *    返回码code：
 *      成功：20000
 *      失败：20001
 *      未登录：10002
 *      未授权：10003
 *      抛出异常：99999
 */
public enum  ResultCode {
    SUCCESS(true,20000,"成功"),
    ERROR(true,20001,"失败");

    boolean success;
    int code;
    String message;

    ResultCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
