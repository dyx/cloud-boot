package com.cloud.boot.common.core.exception;

import com.cloud.boot.common.core.constant.enums.GlobalErrorCodeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 业务异常
 * @author lhd
 */
@Getter
@Setter
public class BizException extends RuntimeException {

    /**
     * 错误编码
     */
    private Integer code;

    /**
     * 错误数据
     */
    private Object data;

    public BizException(String msg) {
        this(GlobalErrorCodeEnum.BUSINESS_ERROR.getCode(), msg, null);
    }

    public BizException(String msg, Throwable t) {
        this(GlobalErrorCodeEnum.BUSINESS_ERROR.getCode(), msg, t);
    }

    public BizException(GlobalErrorCodeEnum errorCodeEnum) {
        this(errorCodeEnum.getCode(), errorCodeEnum.getMsg(), null);
    }

    public BizException(GlobalErrorCodeEnum errorApiStatusEnum, Throwable t) {
        this(errorApiStatusEnum.getCode(), errorApiStatusEnum.getMsg(), t);
    }

    public BizException(Integer code, String msg, Throwable t) {
        this(code, null, msg, t);
    }

    public BizException(Integer code, Object data, String msg, Throwable t) {
        super(msg, t);
        this.code = code;
        this.data = data;
    }
}
