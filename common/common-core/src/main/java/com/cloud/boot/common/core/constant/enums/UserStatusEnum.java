package com.cloud.boot.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lhd
 */
@Getter
@AllArgsConstructor
public enum UserStatusEnum {

    ENABLED("1", "启用"),
    DISABLED("2", "停用");

    private final String value;
    private final String label;
}
