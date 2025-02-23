package com.cloud.boot.common.core.util;

import com.cloud.boot.common.core.constant.CommonConstant;
import com.cloud.boot.common.core.constant.enums.GlobalErrorCodeEnum;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * 统一接口返回值
 * @param <T>
 * @author lhd
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class R<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 错误编码
     */
    private Integer code;
    /**
     * 响应信息
     */
    private String msg;
    /**
     * 响应数据
     */
    private T data;

    public static <T> R<T> ok() {
        return new R<>(CommonConstant.REST_SUCCESS_CODE, "", null);
    }

    public static <T> R<T> ok(T data) {
        return new R<>(CommonConstant.REST_SUCCESS_CODE, "", data);
    }

    public static <T> R<T> fail(Integer code, String msg) {
        return new R<>(code, msg, null);
    }

    public static <T> R<T> fail(GlobalErrorCodeEnum globalErrorCodeEnum) {
        return new R<>(globalErrorCodeEnum.getCode(), globalErrorCodeEnum.getMsg(), null);
    }
}
