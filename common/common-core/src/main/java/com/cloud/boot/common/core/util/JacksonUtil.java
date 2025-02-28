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
        configObjectMapper(OBJECT_MAPPER);
    }

    public static String toJsonStr(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        }
        catch (Exception ignored) {
        }

        return "";
    }

    public static void configObjectMapper(ObjectMapper objectMapper) {
        if (objectMapper != null) {
            objectMapper.setDateFormat(new SimpleDateFormat(CommonConstant.NORMAL_DATETIME_FORMATTER));
            objectMapper.registerModule(new LocalDateTimeModule());
        }
    }
}
