package com.cloud.boot.common.translation.metadata;

import com.cloud.boot.common.translation.annotaion.TranslatorTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Field;

/**
 * @author lhd
 */
@Data
@AllArgsConstructor
public class TargetField {
    private TranslatorTypeEnum translatorType;
    private String dictCode;
    private Field sourceField;
    private String mappingKey;
    private String targetFieldName;
}
