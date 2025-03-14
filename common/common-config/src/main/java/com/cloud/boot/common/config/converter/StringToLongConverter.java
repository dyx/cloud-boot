package com.cloud.boot.common.config.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author lhd
 */
@Component
public class StringToLongConverter implements Converter<String, Long> {
    @Override
    public Long convert(String str) {
        if (str.isEmpty()) {
            return null;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("无效的 Long 类型值: " + str);
        }
    }
}