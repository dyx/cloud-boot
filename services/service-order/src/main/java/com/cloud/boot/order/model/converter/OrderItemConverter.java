package com.cloud.boot.order.model.converter;

import com.cloud.boot.order.model.dto.SaveOrderItemDTO;
import com.cloud.boot.order.model.entity.OrderItemDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lhd
 * <p></p>
 * 订单 实体转换器
 */
@Mapper
public interface OrderItemConverter {

    OrderItemConverter INSTANCE = Mappers.getMapper( OrderItemConverter.class );

    /**
     * dtoToDO
     * @param dto
     * @return
     */
    OrderItemDO dtoToDO(SaveOrderItemDTO dto);
}