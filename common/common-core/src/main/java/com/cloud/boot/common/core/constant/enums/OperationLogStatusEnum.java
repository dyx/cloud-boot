package com.cloud.boot.common.core.constant.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author lhd
 */
@Getter
@RequiredArgsConstructor
public enum OperationLogStatusEnum {

    SUCCESS("1", "成功"),
    FAILURE("2", "失败");

    private final String value;
    private final String label;
}
