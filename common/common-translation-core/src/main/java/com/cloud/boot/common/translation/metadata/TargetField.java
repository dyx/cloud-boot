package com.cloud.boot.common.translation.metadata;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Field;

/**
 * @author lhd
 */
@Data
@AllArgsConstructor
public class TargetField {
    private String translatorType;
    private String dictCode;
    private Field sourceField;
    private String mappingKey;
    private String targetFieldName;
}
