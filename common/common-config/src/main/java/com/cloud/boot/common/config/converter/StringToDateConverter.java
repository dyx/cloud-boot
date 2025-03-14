package com.cloud.boot.common.config.converter;

import com.cloud.boot.common.core.constant.CommonConstant;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author lhd
 */
public class StringToDateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String str) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(CommonConstant.NORMAL_DATETIME_FORMATTER);
            format.setTimeZone(TimeZone.getTimeZone(CommonConstant.TIME_ZONE_ASIA_SHANGHAI));
            return format.parse(str);
        } catch (ParseException e) {
            throw new IllegalArgumentException("日期格式转换错误，应为: " + CommonConstant.NORMAL_DATETIME_FORMATTER);
        }
    }
}