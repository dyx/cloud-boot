package com.cloud.boot.common.translation.translator.impl;

import com.cloud.boot.common.core.util.RHandler;
import com.cloud.boot.common.translation.annotaion.TranslatorTypeEnum;
import com.cloud.boot.common.translation.feign.UserServiceFeignClient;
import com.cloud.boot.common.translation.translator.Translator;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Set;

/**
 * @author lhd
 */
@RequiredArgsConstructor
public class DictTranslator implements Translator<String> {

    private final UserServiceFeignClient translateFeignClient;

    @Override
    public Map<String, Map<String, Object>> translate(Set<String> sourceValueSet) {
        return RHandler.of(translateFeignClient.batchTranslateDict(sourceValueSet)).getData();
    }

    @Override
    public TranslatorTypeEnum getType() {
        return TranslatorTypeEnum.DICT;
    }
}
