package com.zhangyong.exception;

import com.zhangyong.result.CodeMsg;

import java.io.Serializable;

/**
 * <p>ClassName:  </p>
 * <p>Description: 全局异常</p>
 * <p>Company: http://www.shopin.net</p>
 *
 * @author zhangyong@shopin.cn
 * @version 1.0.0
 * @date 2018/10/7 15:52
 */
public class GlobalException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 5011740881247483628L;

    private CodeMsg codeMsg;

    public GlobalException(CodeMsg codeMsg) {
        super(codeMsg.toString());
        this.codeMsg = codeMsg;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }

    public void setCodeMsg(CodeMsg codeMsg) {
        this.codeMsg = codeMsg;
    }

}
