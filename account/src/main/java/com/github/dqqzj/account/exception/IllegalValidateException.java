package com.github.dqqzj.account.exception;

/**
 * @author qinzhongjian
 * @date created in 2018/6/26 11:58
 * @since 1.0.0
 */
public class IllegalValidateException extends RuntimeException {
    private static final long serialVersionUID = 8096590956382108583L;

    public IllegalValidateException(String message) {
        super(message);
    }

    public IllegalValidateException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalValidateException(Throwable cause) {
        super(cause);
    }

    protected IllegalValidateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
