package com.github.dqqzj.account.exception;

/**
 * @author qinzhongjian
 * @date created in 2018/6/26 11:59
 * @since 1.0.0
 */
public class RandomlyException extends RuntimeException {
    private static final long serialVersionUID = 8009088829199901509L;

    public RandomlyException() {
        super();
    }

    public RandomlyException(String message) {
        super(message);
    }

    public RandomlyException(String message, Throwable cause) {
        super(message, cause);
    }

    public RandomlyException(Throwable cause) {
        super(cause);
    }

    protected RandomlyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

