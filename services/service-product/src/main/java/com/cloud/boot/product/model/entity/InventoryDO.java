package com.cloud.boot.product.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.cloud.boot.common.mybatis.base.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lhd
 */
@TableName("pdt_inventory")
@EqualsAndHashCode(callSuper = true)
@Data
public class InventoryDO extends BaseDO {

    private Long productId;

    private Integer stock;

    @Version
    private Integer version;
}