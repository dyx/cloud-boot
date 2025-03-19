package com.cloud.boot.common.translation.translator;


import java.util.Map;
import java.util.Set;

/**
 * @author lhd
 */
public interface Translator<T> {

    Map<T, Map<String, Object>> translate(Set<T> sourceValueSet);
    String getType();
}