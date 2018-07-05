package com.github.dqqzj.common.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * @author qinzhongjian
 * @date created in 2018/6/26 12:08
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@RefreshScope
@ConfigurationProperties(prefix = DelayProperties.PREFIX)
public class DelayProperties {

    public static final String PREFIX = "solar.delay";

    /**
     * 延迟返回毫秒时间
     */
    private long timeInMillseconds;

}
