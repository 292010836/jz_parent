package com.jz1yer.commonutils;

/**
 * 数据响应对象
 *    {
 *      success ：是否成功
 *      code    ：返回码
 *      message ：返回信息
 *      //返回数据
 *      data：  ：{
 *
 *      }
 *    }
 */
public class Result {
    private boolean success;
    private Integer code;
    private String message;
    private Object data;

    public Result(ResultCode code,Object data) {
        this.success = code.success;
        this.code = code.code;
        this.message =code. message;
        this.data = data;
    }
    public Result(ResultCode code) {
        this.success = code.success;
        this.code = code.code;
        this.message =code. message;

    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result SUCCESS(){
        return  new Result(ResultCode.SUCCESS);
    }
    public static Result ERROR(){
        return  new Result(ResultCode.ERROR);
    }
}
