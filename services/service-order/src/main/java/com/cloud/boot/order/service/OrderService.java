package com.cloud.boot.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.boot.order.model.dto.SaveOrderDTO;
import com.cloud.boot.order.model.entity.OrderDO;
import com.cloud.boot.order.model.vo.OrderVO;

/**
 * @author lhd
 * 订单 服务类
 */
public interface OrderService extends IService<OrderDO> {
    void saveOrder(SaveOrderDTO dto);
}
