package com.github.dqqzj.common.aspect;

import com.github.dqqzj.common.config.RequestAttributeConst;
import com.github.dqqzj.common.web.RequestDetailsLogger;
import com.github.dqqzj.common.web.ResponseDetailsLogger;
import com.github.dqqzj.common.web.ServletContextHolder;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;

import java.lang.reflect.Method;
import java.time.OffsetDateTime;

/**
 * @author qinzhongjian
 * @date created in 2018/6/26 12:40
 * @since 1.0.0
 */
@Aspect
@Slf4j
public class RequestLoggingAspect implements Ordered {
    private final int order;

    public RequestLoggingAspect(int order) {
        this.order = order;
    }

    @Around(value = "within(com.github.dqqzj..*) " +
            "&& (@annotation(org.springframework.web.bind.annotation.ResponseBody)" +
            "|| @annotation(org.springframework.web.bind.annotation.RequestMapping)) " +
            "&& @annotation(com.github.dqqzj.common.annotation.RequestLogging)")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        // 生成请求日志
        RequestDetailsLogger requestLog = generateJsonRequestDetails();
        // 获取Swagger上的API描述
        injectApiOperationDescription(joinPoint, requestLog);
        // 执行真实请求
        final Object proceed = joinPoint.proceed();
        // 当响应完成时, 打印完整的'request & response'信息
        requestLog.setResponseTime(OffsetDateTime.now());
        log.debug("RequestLoggingAspect#\r\nREQUEST->\r\n{}\r\nRESPONSE->\r\n {}", requestLog, ResponseDetailsLogger.with(proceed));
        // 放行
        return proceed;
    }

    /**
     * 创建通用的日志输出模式并绑定线程
     *
     * @return 日志模型
     */
    private RequestDetailsLogger generateJsonRequestDetails() {
        RequestDetailsLogger logDetails = (RequestDetailsLogger) ServletContextHolder.getRequest().getAttribute(RequestAttributeConst.DETAILS_KEY);
        if (logDetails == null) {
            logDetails = new RequestDetailsLogger();
            ServletContextHolder.getRequest().setAttribute(RequestAttributeConst.DETAILS_KEY, logDetails);
        }
        return logDetails;
    }

    private void injectApiOperationDescription(ProceedingJoinPoint joinPoint, RequestDetailsLogger logDetails) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        final ApiOperation operate = method.getAnnotation(ApiOperation.class);
        if (operate != null) {
            logDetails.setApiDesc(operate.value());
        }
    }

    @Override
    public int getOrder() {
        return order;
    }
}
