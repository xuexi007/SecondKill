package com.zhangyong.result;

import lombok.Data;

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
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public Result(T data) {
        this.data = data;
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
}
