package com.cloud.boot.common.translation.aspect;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.boot.common.translation.annotaion.*;
import com.cloud.boot.common.translation.metadata.TargetField;
import com.cloud.boot.common.translation.translator.Translator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lhd
 */
@Aspect
public class TranslationAspect {

    private final Map<String, Translator<?>> translatorMap;

    public TranslationAspect(List<Translator<?>> translators) {
        this.translatorMap = translators.stream()
                .collect(Collectors.toMap(Translator::getType, translator -> translator));
    }

    @Around("@annotation(com.cloud.boot.common.translation.annotaion.EnableTranslation)")
    public Object doTranslation(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();

        if (result == null) {
            return result;
        }

        if (result instanceof List<?> list) {
            processList(list);
        }
        else if (result instanceof IPage<?> page) {
            processList(page.getRecords());
        }
        else if (result instanceof Map || result.getClass().isArray()) {
            return result;
        }
        else {
            processSingle(result);
        }

        return result;
    }

    private void processSingle(Object entry) {
        processList(Collections.singletonList(entry));
    }

    private void processList(List<?> entryList) {

        if (CollUtil.isEmpty(entryList) || entryList.get(0) == null) {
            return;
        }

        // 按照翻译类型合并 sourceValue，确保每种类型只请求一次
        Map<String, Set<?>> sourceValueMap = new HashMap<>();
        List<TargetField> targetFiledList = new ArrayList<>();
        collectParams(entryList, sourceValueMap, targetFiledList);

        if (CollUtil.isEmpty(sourceValueMap)) {
            return;
        }

        Map<String, Map<String, Object>> targetValueMap = getTargetValue(sourceValueMap);
        setTargetValue(entryList, targetFiledList, targetValueMap);
    }

    @SuppressWarnings("unchecked")
    private <T> void collectParams(List<?> entryList, Map<String, Set<?>> sourceValueMap, List<TargetField> targetFiledList) {

        for (Field field : entryList.get(0).getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Translate.class)) {

                Translate translateAnnotation = field.getAnnotation(Translate.class);
                String translatorType = translateAnnotation.type();

                Set<T> sourceValueSet = (Set<T>) sourceValueMap.computeIfAbsent(translatorType, k -> new HashSet<>());
                if (TranslatorTypeConstant.DICT.equals(translatorType)) {
                    String dictCode = translateAnnotation.dictCode();
                    // DICT类型，sourceValue为dict_code，其他类型为主键id
                    sourceValueSet.add((T) dictCode);
                    // DICT 类型的 targetFieldName 为 sourceFieldName+Name 后缀拼接而成
                    targetFiledList.add(new TargetField(translatorType, dictCode, field, null, String.format("%s%s", field.getName(), "Name")));
                }
                else {
                    for (Object entry : entryList) {
                        T sourceValue = (T) ReflectUtil.getFieldValue(entry, field);
                        sourceValueSet.add(sourceValue);
                    }
                    for (TranslateMapping translateMapping : translateAnnotation.target()) {
                        targetFiledList.add(new TargetField(translatorType, "", field, translateMapping.mappingKey(), translateMapping.target()));
                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private <T> Map<String, Map<String, Object>> getTargetValue(Map<String, Set<?>> sourceValueMap) {

        Map<String, Map<String, Object>> targetValueMap = new HashMap<>();
        for (Map.Entry<String, Set<?>> sourceEntry : sourceValueMap.entrySet()) {

            String translatorType = sourceEntry.getKey();
            Translator<T> translator = (Translator<T>) translatorMap.get(translatorType);
            if (translator == null) {
                continue;
            }

            Set<T> sourceValueSet = (Set<T>) sourceEntry.getValue();
            Map<T, Map<String, Object>> remoteTargetValueMap = translator.translate(sourceValueSet);
            if (remoteTargetValueMap == null) {
                continue;
            }

            for (Map.Entry<T, Map<String, Object>> targetEntry : remoteTargetValueMap.entrySet()) {
                targetValueMap.put(getTargetValueMapKey(translatorType, targetEntry.getKey()), targetEntry.getValue());
            }
        }

        return targetValueMap;
    }

    @SuppressWarnings("unchecked")
    private <T> void setTargetValue(List<?> entryList, List<TargetField> targetFiledList, Map<String, Map<String, Object>> targetValueMap) {

        for (Object entry : entryList) {
            for (TargetField targetField : targetFiledList) {

                T sourceValue = (T) ReflectUtil.getFieldValue(entry, targetField.getSourceField());
                if (TranslatorTypeConstant.DICT.equals(targetField.getTranslatorType())) {
                    Map<String, Object> map = targetValueMap.get(getTargetValueMapKey(targetField.getTranslatorType(), targetField.getDictCode()));
                    if (map == null) {
                        continue;
                    }
                    Object targetValue = map.get(Convert.toStr(sourceValue));
                    ReflectUtil.setFieldValue(entry, targetField.getTargetFieldName(), targetValue);
                }
                else {
                    Map<String, Object> map = targetValueMap.get(getTargetValueMapKey(targetField.getTranslatorType(), sourceValue));

                    if (map == null) {
                        continue;
                    }

                    Object targetValue = map.get(targetField.getMappingKey());
                    ReflectUtil.setFieldValue(entry, targetField.getTargetFieldName(), targetValue);
                }
            }
        }
    }

    private String getTargetValueMapKey(String translatorType, Object sourceValue) {
        return String.format(String.format("%s:%s", translatorType, Convert.toStr(sourceValue)));
    }
}