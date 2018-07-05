package com.github.dqqzj.common.config;

/**
 * @author qinzhongjian
 * @date created in 2018/6/26 10:51
 * @since 1.0.0
 */
public final class RequestAttributeConst {
    public static final String DETAILS_KEY = "X-Logs-Details";
    public static final String REQUEST_BODY_KEY = "X-Request-Body";
    public static final String REQUEST_ID = "X-Request-Id";

    private RequestAttributeConst() {
        throw new IllegalStateException("do not try to use reflection");
    }

}

