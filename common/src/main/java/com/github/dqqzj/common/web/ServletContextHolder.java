package com.github.dqqzj.common.web;

import com.github.dqqzj.common.config.RequestAttributeConst;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;

/**
 * @author qinzhongjian
 * @date created in 2018/6/26 10:36
 * @since 1.0.0
 */
public final class ServletContextHolder {

    private ServletContextHolder() {
    }

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    public static String fetchRequestId() {
        String requestId = (String) getRequest().getAttribute(RequestAttributeConst.REQUEST_ID);
        if (requestId == null) {
            requestId = Optional.ofNullable(getRequest().getHeader(RequestAttributeConst.REQUEST_ID)).orElse("x-" + UUID.randomUUID());
            getRequest().setAttribute(RequestAttributeConst.REQUEST_ID, requestId);
        }
        return requestId;
    }
}

