package com.cloud.boot.common.mybatis.util;

import com.cloud.boot.common.mybatis.constant.SensitiveStrategy;

/**
 * @author lhd
 */
public class SensitiveUtils {
    public static String desensitize(String input, SensitiveStrategy strategy) {
        if (input == null) {
            return null;
        }
        return switch (strategy) {
            case PHONE -> input.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
            case ID_CARD -> input.replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1****$2");
            case EMAIL -> input.replaceAll("(\\w{2})[^@]+@(\\w+)", "$1****@$2");
        };
    }
}