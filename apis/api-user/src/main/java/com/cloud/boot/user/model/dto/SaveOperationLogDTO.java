package com.cloud.boot.user.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lhd
 */
@Data
public class SaveOperationLogDTO {

    /**
     * 操作模块
     */
    @NotBlank
    private String module;

    /**
     * 操作类型（新增/修改/删除等）
     */
    @NotBlank
    private String type;

    /**
     * 操作人ID
     */
    @NotNull
    private Long operatorId;

    /**
     * 操作时间
     */
    @NotNull
    private LocalDateTime operationTime;

    /**
     * 操作IP
     */
    private String operationIp;

    /**
     * 操作浏览器信息
     */
    private String operationUserAgent;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 返回结果
     */
    private String result;

    /**
     * 操作状态（1成功 2失败）
     */
    private String status;

    /**
     * 操作耗时（毫秒）
     */
    private Long elapsedTime;

    /**
     * 错误信息
     */
    private String errorMsg;
}