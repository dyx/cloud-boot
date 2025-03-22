package com.cloud.boot.user.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lhd
 */
@TableName("sys_operation_log")
@Data
public class SysOperationLogDO {

    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 操作模块
     */
    private String module;

    /**
     * 操作类型（新增/修改/删除等）
     */
    private String type;

    /**
     * 操作人ID
     */
    private Long operatorId;

    /**
     * 操作时间
     */
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
    private Integer elapsedTime;

    /**
     * 错误信息
     */
    private String errorMsg;
}