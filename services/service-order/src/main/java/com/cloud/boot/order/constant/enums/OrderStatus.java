package com.cloud.boot.order.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author lhd
 * <p></p>
 * 订单状态
 */
@Getter
@AllArgsConstructor
public enum OrderStatus {
    UNPAID(0, "待支付"),
    PAID(1, "已支付"),
    SHIPPED(2, "已发货"),
    COMPLETED(3, "已完成"),
    CANCELLED(4, "已取消");

    private final int code;
    private final String desc;

}