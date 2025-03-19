package com.cloud.boot.user.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.boot.user.mapper.SysDictMapper;
import com.cloud.boot.user.model.entity.SysDictDO;
import com.cloud.boot.user.service.SysDictService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lhd
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDictDO> implements SysDictService {

    @Override
    public Map<String, Map<String, Object>> batchTranslateDict(Set<String> sourceValueSet) {
        Map<String, Map<String, Object>> resultMap = new HashMap<>();
        if (CollUtil.isEmpty(sourceValueSet)) {
            return resultMap;
        }
        List<SysDictDO> doList = list(Wrappers.<SysDictDO>lambdaQuery().in(SysDictDO::getCode, sourceValueSet));
        for (SysDictDO dataObj : doList) {
            Map<String, Object> map = resultMap.computeIfAbsent(dataObj.getCode(), k -> new HashMap<>());
            map.put(dataObj.getValue(), dataObj.getLabel());
        }
        return resultMap;
    }
}
