package com.cloud.boot.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.boot.user.mapper.SysOperationLogMapper;
import com.cloud.boot.user.model.converter.SysOperationLogConverter;
import com.cloud.boot.user.model.dto.SaveOperationLogDTO;
import com.cloud.boot.user.model.entity.SysOperationLogDO;
import com.cloud.boot.user.service.SysOperationLogService;
import org.springframework.stereotype.Service;

/**
 * @author lhd
 */
@Service
public class SysOperationLogServiceImpl extends ServiceImpl<SysOperationLogMapper, SysOperationLogDO> implements SysOperationLogService {

    @Override
    public void saveOperationLog(SaveOperationLogDTO dto) {
        save(SysOperationLogConverter.INSTANCE.saveDto2Do(dto));
    }
}
