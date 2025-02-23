package com.cloud.boot.product.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author lhd
 */
@Schema(description = "扣减库存参数")
@Data
public class DeductStockDTO {

    @Schema(description = "商品ID")
    @Min(1)
    @NotNull
    private Long productId;

    @Schema(description = "库存")
    @Min(1)
    @NotNull
    private Integer stock;
}
