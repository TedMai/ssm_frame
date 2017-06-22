package com.yingjun.ssm.exception;


import com.yingjun.ssm.common.result.ResultEnum;

/**
 * 自定义异常
 */
public class SSMException extends RuntimeException{

    private Integer code;

    public SSMException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
