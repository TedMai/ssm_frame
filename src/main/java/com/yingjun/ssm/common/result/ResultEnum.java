package com.yingjun.ssm.common.result;

/**
 * 异常枚举
 */
public enum ResultEnum {

    //通用错误
    SUCCESS(1, "成功"),
    FAIL(0, "失败"),
    UNKONW_ERROR(-1, "未知错误"),

    // 数据库想操作异常
    DB_INSERT_RESULT_ERROR(99990001, "db insert error"),
    DB_UPDATE_RESULT_ERROR(99990002, "db update error"),
    DB_SELECTONE_IS_NULL(99990003,"db select return null"),

    // 系统异常
    INNER_ERROR(99980001, "系统错误"),
    TOKEN_IS_ILLICIT(99980002, "Token验证非法"),
    SESSION_IS_OUT_TIME(99980003, "会话超时"),
    NUllPOINTER_ERROR(99980004,"空指针异常"),
    CACHE_ERROR(99980005,"缓存访问错误"),
    DAO_ERROR(99980006,"数据访问错误"),

    // 用户相关异常
    INVALID_USER(1001001, "无效用户"),

    // 业务相关异常
    VALIDATE_ERROR(2001001,"参数错误"),
    SERVICE_ERROR(2001002,"业务逻辑异常");


    private Integer code;
    private String msg;
    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static ResultEnum codeOf(int index) {
        for (ResultEnum resultEnum : values()) {
            if (resultEnum.getCode() == index) {
                return resultEnum;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "ResultEnum{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
