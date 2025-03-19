package com.cloud.boot.common.translation.annotaion;

/**
 * @author lhd
 */
public enum TranslatorTypeEnum implements TranslatorType {
    DICT("dict"),
    USER("user");

    private final String code;

    TranslatorTypeEnum(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }
}
