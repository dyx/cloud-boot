package com.cloud.boot.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lhd
 * <p></p>
 * 全局错误码
 * <p>业务错误码，起始值100000，每1000个为一大类</p>
 */
@Getter
@AllArgsConstructor
public enum GlobalErrorCodeEnum {

    /**
     * 业务异常
     */
    BUSINESS_ERROR(100000, "业务异常"),
    /**
     * sentinel异常
     */
    SENTINEL_TOO_MANY_REQUESTS(1200, "请求太多，请稍后重试"),
    /**
     * 关系型数据库
     */
    DB_DATA_NO_DEFAULT_VALUE(1102, "非空字段没有默认值"),
    DB_DATA_TOO_LONG(1101, "字段超长"),
    DB_DATA_INTEGRITY_VIOLATION(1100, "数据完整性异常"),
    /**
     * 接口访问错误
     */
    REST_SERVICE_UNAVAILABLE(1005, "服务不可用"),
    REST_ACCESS_DENIED(1004, "访问被拒绝"),
    REST_MEDIA_TYPE_ERROR(1003, "不支持的媒体类型"),
    REST_REQUEST_METHOD_ERROR(1002,"请求方式错误"),
    REST_PARAM_VALIDATE_ERROR(1001,"请求参数校验错误"),

    UNKNOWN_ERROR(1000, "未知异常");

    private final Integer code;
    private final String msg;
}
