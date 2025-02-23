package com.cloud.boot.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.boot.product.model.entity.ProductDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lhd
 */
@Mapper
public interface ProductMapper extends BaseMapper<ProductDO> {
}
