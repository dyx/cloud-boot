package com.cloud.boot.product.model.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
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

    @TableId
    private Long id;

    private Long productId;

    private Integer stock;

    @Version
    private Integer version;
}