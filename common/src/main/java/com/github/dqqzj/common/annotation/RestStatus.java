package com.github.dqqzj.common.annotation;

/**
 * @author qinzhongjian
 * @date created in 2018/6/25 18:55
 * @since 1.0.0
 */
public interface RestStatus {
    /**
     * the status codes of per restful request.
     *
     * @return 20xxx if succeed, 40xxx if client error, 50xxx if server side crash.
     */
    int code();

    /**
     * @return message summary
     */
    String message();

}
