package com.github.dqqzj.common.aspect;

import com.github.dqqzj.common.config.RequestAttributeConst;
import com.github.dqqzj.common.web.ServletContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;

import javax.servlet.http.HttpServletResponse;

/**
 * @author qinzhongjian
 * @date created in 2018/6/26 12:38
 * @since 1.0.0
 */
@Aspect
public class RequestIdStuffAspect implements Ordered {
    private final int order;

    public RequestIdStuffAspect(int order) {
        this.order = order;
    }

    @Before(value = "within(com.github.dqqzj..*) " +
            "&& (@annotation(org.springframework.web.bind.annotation.ResponseBody)" +
            "|| @annotation(org.springframework.web.bind.annotation.RequestMapping))")
    public void before() {
        final String requestId = ServletContextHolder.fetchRequestId();
        final HttpServletResponse response = ServletContextHolder.getResponse();
        if (response.getHeader(RequestAttributeConst.REQUEST_ID) == null) {
            response.addHeader(RequestAttributeConst.REQUEST_ID, requestId);
        }
    }

    @Override
    public int getOrder() {
        return order;
    }
}