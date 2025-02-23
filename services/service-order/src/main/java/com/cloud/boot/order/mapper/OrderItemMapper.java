package com.cloud.boot.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.boot.order.model.entity.OrderDO;
import com.cloud.boot.order.model.entity.OrderItemDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lhd
 * 订单明细 Mapper 接口
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItemDO> {

}
