package com.cloud.boot.common.feign.converter;

import com.cloud.boot.common.core.constant.CommonConstant;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author lhd
 */
public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String str) {
        return LocalDateTime.parse(str,  DateTimeFormatter.ofPattern(CommonConstant.NORMAL_DATETIME_FORMATTER));
    }
}