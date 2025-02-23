package com.cloud.boot.order.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author lhd
 */
@Schema(description = "保存订单明细参数")
@Data
public class SaveOrderItemDTO {

    @Schema(description = "商品ID")
    @NotNull
    private Long productId;

    @Schema(description = "商品名称")
    @NotBlank
    private String productName;

    @Schema(description = "商品数量")
    @NotNull
    private Integer quantity;

    @Schema(description = "商品单价")
    @NotNull
    private BigDecimal price;
}
