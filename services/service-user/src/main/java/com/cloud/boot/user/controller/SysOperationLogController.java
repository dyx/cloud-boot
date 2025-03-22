package com.cloud.boot.user.controller;

import com.cloud.boot.common.core.util.R;
import com.cloud.boot.user.model.dto.SaveOperationLogDTO;
import com.cloud.boot.user.service.SysOperationLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lhd
 */
@Tag(name = "操作日志接口")
@RequestMapping("/operation-log")
@RestController
@RequiredArgsConstructor
public class SysOperationLogController {

    private final SysOperationLogService sysOperationLogService;

    @PostMapping
    R<?> saveOperationLog(@Valid @RequestBody SaveOperationLogDTO dto) {
        sysOperationLogService.saveOperationLog(dto);
        return R.ok();
    }
}
