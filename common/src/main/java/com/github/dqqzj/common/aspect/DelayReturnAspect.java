package com.github.dqqzj.common.aspect;

import com.github.dqqzj.common.config.DelayProperties;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;

import java.util.concurrent.TimeUnit;


/**
 * @author qinzhongjian
 * @date created in 2018/6/26 12:02
 * @since 1.0.0
 */
@Aspect
@Slf4j
public class DelayReturnAspect implements Ordered {
    private final int order;
    private final DelayProperties delayProperties;

    public DelayReturnAspect(int order, DelayProperties delayProperties) {
        this.order = order;
        this.delayProperties = delayProperties;
    }

    @Around("@annotation(com.github.dqqzj.common.annotation.Delay)")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        final Object result = joinPoint.proceed();
        final long timeInMillseconds = delayProperties.getTimeInMillseconds();
        if (timeInMillseconds != 0L) {
            log.debug("method {} was made delay {} mills to return result", joinPoint.getSignature(), timeInMillseconds);
            TimeUnit.MILLISECONDS.sleep(timeInMillseconds);
        }
        return result;
    }

    @Override
    public int getOrder() {
        return order;
    }
}

