package com.cloud.boot.common.core.util;

import com.cloud.boot.common.core.constant.CommonConstant;
import com.cloud.boot.common.core.constant.enums.GlobalErrorCodeEnum;
import com.cloud.boot.common.core.exception.BizException;

import java.util.Optional;

/**
 * @author lhd
 * @param <T>
 */
public class RHandler<T> {
    private final R<T> result;

    private RHandler(R<T> result) {
        this.result = result;
    }

    public static <T> RHandler<T> of(R<T> result) {
        return new RHandler<>(result);
    }

    public Optional<T> getOptionalData() {
        return Optional.ofNullable(result != null ? result.getData() : null);
    }

    public T getData() {
        return result != null ? result.getData() : null;
    }

    public void failThenThrow() {
        if (result == null) {
            throw new BizException(GlobalErrorCodeEnum.UNKNOWN_ERROR);
        }
        if (!CommonConstant.REST_SUCCESS_CODE.equals(result.getCode())) {
            throw new BizException(result.getMsg());
        }
    }
}
