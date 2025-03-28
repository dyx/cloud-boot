package com.cloud.boot.product.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.boot.common.mybatis.base.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @author lhd
 */
@TableName("pdt_product")
@EqualsAndHashCode(callSuper = true)
@Data
public class ProductDO extends BaseDO {

    private String name;

    private BigDecimal price;

    private String status;
}