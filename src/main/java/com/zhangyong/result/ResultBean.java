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
public class ResultBean<T> implements Serializable {
    private static final long serialVersionUID = 9085590659948791191L;

    public static final int SUCCESS = 0;
    public static final int FAIL = 1;
    public static final int NO_PERMISSION = 2;

    private String msg = "success";
    private int code = SUCCESS;
    private T data;

    public ResultBean() {
        super();
    }

    public ResultBean(T data) {
        this.data = data;
    }

    private ResultBean(CodeMsg codeMsg) {
        if (codeMsg != null) {
            this.code = codeMsg.getCode();
            this.msg = codeMsg.getMsg();
        }
    }

    public static <T> ResultBean<T> success(T data) {
        return new ResultBean<T>(data);
    }

    public static <T> ResultBean<T> error(CodeMsg codeMsg) {
        return new ResultBean<T>((T) codeMsg);
    }
}
