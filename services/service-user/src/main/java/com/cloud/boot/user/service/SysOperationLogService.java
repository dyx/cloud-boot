package com.cloud.boot.user.service;

import com.cloud.boot.user.model.dto.SaveOperationLogDTO;

/**
 * @author lhd
 */
public interface SysOperationLogService {

    void saveOperationLog(SaveOperationLogDTO dto);
}
