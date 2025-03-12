package com.cloud.boot.common.core.jackson;

import com.cloud.boot.common.core.constant.CommonConstant;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author lhd
 */
public class CustomDateDeserializer extends DateDeserializers.DateDeserializer {

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String dateStr = p.getText();
        try {
            SimpleDateFormat format = new SimpleDateFormat(CommonConstant.NORMAL_DATETIME_FORMATTER);
            format.setTimeZone(TimeZone.getTimeZone(CommonConstant.TIME_ZONE_ASIA_SHANGHAI));
            return format.parse(dateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("反序列化日期错误，格式应为 " + CommonConstant.NORMAL_DATETIME_FORMATTER);
        }
    }
}
