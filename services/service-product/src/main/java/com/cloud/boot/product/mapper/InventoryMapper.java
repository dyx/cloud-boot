package com.cloud.boot.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.boot.product.model.entity.InventoryDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lhd
 */
@Mapper
public interface InventoryMapper extends BaseMapper<InventoryDO> {
}
