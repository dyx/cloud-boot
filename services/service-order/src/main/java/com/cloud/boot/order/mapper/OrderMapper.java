package com.cloud.boot.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.boot.order.model.entity.OrderDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lhd
 * 订单 Mapper 接口
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderDO> {

}
