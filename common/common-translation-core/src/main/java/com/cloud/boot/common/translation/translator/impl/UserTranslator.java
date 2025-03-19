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
public class UserTranslator implements Translator<Long> {

    private final UserServiceFeignClient translateFeignClient;

    @Override
    public Map<Long, Map<String, Object>> translate(Set<Long> sourceValueSet) {
        return RHandler.of(translateFeignClient.batchTranslateUser(sourceValueSet)).getData();
    }

    @Override
    public TranslatorTypeEnum getType() {
        return TranslatorTypeEnum.USER;
    }
}
