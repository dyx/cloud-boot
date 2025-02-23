package com.cloud.boot.common.core.util;

import com.cloud.boot.common.core.constant.CommonConstant;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

/**
 * @author lhd
 */
public class JacksonUtil {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    static {
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat(CommonConstant.NORMAL_DATETIME_FORMATTER));
        OBJECT_MAPPER.registerModule(new LocalDateTimeModule());
    }

    public static String toJsonStr(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        }
        catch (Exception ignored) {
        }

        return "";
    }
}
