package com.github.dqqzj.common.util;

import java.security.SecureRandom;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @author qinzhongjian
 * @date created in 2018/6/25 22:35
 * @since 1.0.0
 */
public final class GuidGenerator {
    private static final Pattern UUID_DASH_PATTERN = Pattern.compile("-");
    private static final SecureRandom RANDOM = new SecureRandom();

    private GuidGenerator() {
    }

    public static String generate() {
        return UUID_DASH_PATTERN.matcher(UUID.randomUUID().toString()).replaceAll("") + (RANDOM.nextInt(8999) + 1000);
    }

}
