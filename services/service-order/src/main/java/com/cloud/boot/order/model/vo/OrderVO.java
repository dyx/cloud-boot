package com.cloud.boot.order.model.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author lhd
 * 订单 视图对象
 */
@Data
public class OrderVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private String orderNo;

    private BigDecimal totalAmount;

    private Integer orderStatus;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
