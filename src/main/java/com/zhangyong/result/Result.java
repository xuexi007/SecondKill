package com.zhangyong.result;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>ClassName: 结果返回集</p>
 * <p>Description: </p>
 * <p>Company: http://www.shopin.net</p>
 *
 * @author zhangyong@shopin.cn
 * @version 1.0.0
 * @Date 2018/6/26 6:42
 */
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 9085590659948791191L;

    private String msg;
    private int code;
    private T data;

    public Result() {
        super();
    }

    public Result(T data) {
        this.data = data;
    }

    private Result(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

    private Result(CodeMsg codeMsg) {
        if (codeMsg != null) {
            this.code = codeMsg.getCode();
            this.msg = codeMsg.getMsg();
        }
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    public static <T> Result<T> error(CodeMsg codeMsg) {
        return new Result<T>((T) codeMsg);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
