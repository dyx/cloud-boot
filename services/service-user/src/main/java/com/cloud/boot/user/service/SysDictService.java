package com.cloud.boot.user.service;

import java.util.Map;
import java.util.Set;

/**
 * @author lhd
 */
public interface SysDictService {

    Map<String, Map<String, Object>> batchTranslateDict(Set<String> sourceValueSet);
}
