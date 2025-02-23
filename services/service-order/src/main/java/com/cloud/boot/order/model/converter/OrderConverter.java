package com.cloud.boot.order.model.converter;

import com.cloud.boot.order.model.entity.OrderDO;
import com.cloud.boot.order.model.vo.OrderVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lhd
 * <p></p>
 * 订单 实体转换器
 */
@Mapper
public interface OrderConverter {

    OrderConverter INSTANCE = Mappers.getMapper( OrderConverter.class );

    /**
     * doToVO
     * @param orderDO
     * @return
     */
    OrderVO doToVO(OrderDO orderDO);
}