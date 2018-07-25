package com.github.dqqzj.gateway.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author qinzhongjian
 * @date created in 2018/7/25 12:20
 * @since 1.0.0
 */
@Slf4j
public class GoogleGuavaCache {

    public static final Cache<String,Object> CACHE;

    static {
        CACHE = CacheBuilder
                .newBuilder()
                .concurrencyLevel(10)
                .initialCapacity(10)
                .expireAfterAccess(60,TimeUnit.SECONDS)
                .maximumSize(1000)
                .recordStats()
                .removalListener(removalNotification -> log.info("【GoogleGuavaCache】正在清除缓存数据："+removalNotification.getKey()+ "was removed, cause is "+removalNotification.getCause()))
                .build();
    }
}
