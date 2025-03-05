package com.cloud.boot.order.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.boot.common.mybatis.base.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @author lhd
 */
@TableName("ord_order_item")
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderItemDO extends BaseDO {

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品数量
     */
    private Integer quantity;

    /**
     * 商品单价
     */
    private BigDecimal price;

    /**
     * 商品总价
     */
    private BigDecimal totalPrice;
}