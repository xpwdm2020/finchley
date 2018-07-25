package com.github.dqqzj.gateway.enums;

import com.google.common.collect.ImmutableMap;

/**
 * API 统一返回状态码
 * Created by qzj on 2018/3/24
 */
public enum StatusEnum {
    AUTHENTICATION_ERROR(1001,"用户名或密码错误"),
    SMS_CODE_ERROR(1003,"获取阿里云短信码验证失败"),
    SMS_CODE_CHECK_ERROR(1004,"阿里云短信码校验失败"),
    SESSION_INVALID(3001,"session过期"),
    // 40xxx 客户端不合法的请求
    INVALID_MODEL_FIELDS(4001, "字段校验非法"),
    /**
     * 参数类型非法，常见于SpringMVC中String无法找到对应的enum而抛出的异常
     */
    INVALID_PARAMS_CONVERSION(4002, "参数类型非法"),
    // 41xxx 请求方式出错
    /**
     * http media type not supported
     */
    HTTP_MESSAGE_NOT_READABLE(4003, "HTTP消息不可读"),
    /**
     * 请求方式非法
     */
    REQUEST_METHOD_NOT_SUPPORTED(4004, "不支持的HTTP请求方法"),
    /* 用户错误：20001-29999*/
    ACCOUNT_NOT_LOGGED_IN(2001, "用户未登陆"),
    ACCOUNT_LOGIN_ERROR(2002, "用户账号或密码错误"),
    ACCOUNT_FORBIDDEN(2003, "用户已被禁用"),
    ACCOUNT_NOT_EXIST(2004, "用户不存在"),
    ACCOUNT_EXISTED(2005, "用户已存在"),

    /* 权限错误：70001-79999 */
    PERMISSION_NOT_SUPPORT(7001, "无访问权限");

    private final Integer code;
    private final String message;

    private static final ImmutableMap<Integer, StatusEnum> CACHE;

    static {
        final ImmutableMap.Builder<Integer, StatusEnum> builder = ImmutableMap.builder();
        for (StatusEnum statusEnum : values()) {
            builder.put(statusEnum.getCode(), statusEnum);
        }
        CACHE = builder.build();
    }

    public static StatusEnum valueOfCode(Integer code) {
        final StatusEnum status = CACHE.get(code);
        if (status == null) {
            throw new IllegalArgumentException("No matching constant for [" + code + "]");
        }
        return status;
    }

    StatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
