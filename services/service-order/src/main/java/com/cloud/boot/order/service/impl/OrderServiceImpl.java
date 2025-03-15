package com.cloud.boot.order.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.boot.common.core.exception.BizException;
import com.cloud.boot.order.mapper.OrderMapper;
import com.cloud.boot.order.model.converter.OrderItemConverter;
import com.cloud.boot.order.model.dto.SaveOrderDTO;
import com.cloud.boot.order.model.dto.SaveOrderItemDTO;
import com.cloud.boot.order.model.entity.OrderDO;
import com.cloud.boot.order.model.entity.OrderItemDO;
import com.cloud.boot.order.service.OrderItemService;
import com.cloud.boot.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lhd
 * 订单 服务实现类
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderDO> implements OrderService {

    private final OrderItemService orderItemService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrder(SaveOrderDTO dto) {
        if (CollectionUtil.isEmpty(dto.getOrderItemList())) {
            throw new BizException("订单明细不能为空");
        }

        BigDecimal totalAmount = BigDecimal.ZERO;
        List<OrderItemDO> orderItemDOList = new ArrayList<>();
        for (SaveOrderItemDTO saveOrderItemDTO : dto.getOrderItemList()) {

            OrderItemDO orderItemDO = OrderItemConverter.INSTANCE.dtoToDO(saveOrderItemDTO);
            orderItemDO.setTotalPrice(saveOrderItemDTO.getPrice().multiply(new BigDecimal(saveOrderItemDTO.getQuantity())));
            orderItemDOList.add(orderItemDO);

            totalAmount = totalAmount.add(orderItemDO.getTotalPrice());
        }

        OrderDO orderDO = new OrderDO();
        orderDO.setUserId(0L);
        orderDO.setOrderNo(LocalDateTimeUtil.format(LocalDateTime.now(), "yyMMddHHmmss") + StrUtil.padPre(String.valueOf(RandomUtil.randomInt(1, 1000)), 3,"0"));
        orderDO.setTotalAmount(totalAmount);
        boolean saveFlag = save(orderDO);
        if (!saveFlag) {
            throw new BizException("创建订单失败");
        }

        for (OrderItemDO orderItemDO : orderItemDOList) {
            orderItemDO.setOrderId(orderDO.getId());
        }
        orderItemService.saveBatch(orderItemDOList);
    }
}
