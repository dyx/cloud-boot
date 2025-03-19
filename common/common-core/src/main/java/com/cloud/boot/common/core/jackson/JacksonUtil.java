package com.cloud.boot.common.core.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author lhd
 */
public class JacksonUtil {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    static {
        configObjectMapper(OBJECT_MAPPER);
    }

    public static String toJsonStr(Object obj) {
        if (obj == null) {
            return null;
        }

        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        }
        catch (Exception ignored) {
        }

        return "";
    }

    public static byte[] toJsonBytes(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return OBJECT_MAPPER.writeValueAsBytes(obj);
        }
        catch (Exception ignored) {
        }
        return new byte[0];
    }

    public static void configObjectMapper(ObjectMapper objectMapper) {
        if (objectMapper != null) {
            objectMapper.registerModule(new CustomSimpleModule());
        }
    }
}
