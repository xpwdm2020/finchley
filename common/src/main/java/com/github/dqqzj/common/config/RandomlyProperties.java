package com.github.dqqzj.common.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author qinzhongjian
 * @date created in 2018/6/26 12:13
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@RefreshScope
@ConfigurationProperties(prefix = RandomlyProperties.PREFIX)
public class RandomlyProperties {

    public static final String PREFIX = "solar.exception";

    /**
     * 是否启用随机异常
     */
    private boolean enabled;

    /**
     * 当对此数取余为0就会抛出异常
     */
    private int factor;

}
