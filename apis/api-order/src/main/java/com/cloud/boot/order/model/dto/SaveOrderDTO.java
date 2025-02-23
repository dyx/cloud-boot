package com.cloud.boot.order.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

/**
 * @author lhd
 */
@Schema(description = "创建订单参数")
@Data
public class SaveOrderDTO {

    @Schema(description = "订单明细列表")
    @Valid
    @NotEmpty
    List<SaveOrderItemDTO> orderItemList;
}
