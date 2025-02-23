package com.cloud.boot.order.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.boot.common.mybatis.base.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author lhd
 * 订单 数据对象
 */
@TableName("ord_order")
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderDO extends BaseDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    private Long userId;

    private String orderNo;

    private BigDecimal totalAmount;

    private Integer orderStatus;
}
