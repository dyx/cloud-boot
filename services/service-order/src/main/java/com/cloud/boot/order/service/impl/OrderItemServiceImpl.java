package com.cloud.boot.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.boot.order.mapper.OrderItemMapper;
import com.cloud.boot.order.model.entity.OrderItemDO;
import com.cloud.boot.order.service.OrderItemService;
import org.springframework.stereotype.Service;

/**
 * @author lhd
 * 订单明细 服务实现类
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItemDO> implements OrderItemService {

}
