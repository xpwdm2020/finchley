package com.github.dqqzj.common.aspect;

import com.github.dqqzj.common.config.RandomlyProperties;
import com.github.dqqzj.common.exception.RandomlyException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author qinzhongjian
 * @date created in 2018/6/26 12:37
 * @since 1.0.0
 */
@Aspect
public class RandomlyExceptionAspect implements Ordered {
    private final int order;
    private final RandomlyProperties properties;
    private static final Random RANDOM = new SecureRandom();

    public RandomlyExceptionAspect(int order, RandomlyProperties properties) {
        this.order = order;
        this.properties = properties;
    }

    @Around("@annotation(com.github.dqqzj.common.annotation.RandomlyThrowsException)")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        if (properties.isEnabled()) {
            if (RANDOM.nextInt(100) % properties.getFactor() == 0) {
                throw new RandomlyException("manual exception");
            }
        }
        return joinPoint.proceed();
    }

    @Override
    public int getOrder() {
        return order;
    }
}

