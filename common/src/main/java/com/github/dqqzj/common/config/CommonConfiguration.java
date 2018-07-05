package com.github.dqqzj.common.config;

import com.github.dqqzj.common.aspect.DelayReturnAspect;
import com.github.dqqzj.common.aspect.RandomlyExceptionAspect;
import com.github.dqqzj.common.aspect.RequestIdStuffAspect;
import com.github.dqqzj.common.aspect.RequestLoggingAspect;
import com.github.dqqzj.common.web.filter.ResettableRequestFilter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * @author qinzhongjian
 * @date created in 2018/6/26 12:07
 * @since 1.0.0
 */
@Configuration
@EnableConfigurationProperties({DelayProperties.class, RandomlyProperties.class})
public class CommonConfiguration {
    @Bean
    public DelayReturnAspect delayReturnAspect(DelayProperties properties) {
        return new DelayReturnAspect(Ordered.LOWEST_PRECEDENCE, properties);
    }

    @Bean
    public RandomlyExceptionAspect manualExceptionAspect(RandomlyProperties properties) {
        return new RandomlyExceptionAspect(Ordered.LOWEST_PRECEDENCE - 1, properties);
    }

    @Bean
    public ResettableRequestFilter resettableRequestFilter() {
        return new ResettableRequestFilter();
    }

    @Bean
    public RequestIdStuffAspect idStuffAspect() {
        final int order = Byte.MAX_VALUE;
        return new RequestIdStuffAspect(order);
    }

    @Bean
    public RequestLoggingAspect logsAspect() {
        final int order = Byte.MAX_VALUE + 1;
        return new RequestLoggingAspect(order);
    }


}
