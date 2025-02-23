package com.cloud.boot.product.controller;

import com.cloud.boot.common.core.util.R;
import com.cloud.boot.product.model.dto.DeductStockDTO;
import com.cloud.boot.product.service.InventoryService;
import com.cloud.boot.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lhd
 */
@Validated
@Tag(name = "库存接口")
@RequestMapping("inventory")
@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @Operation(summary = "批量扣减库存")
    @PostMapping("/stock/deduct/batch")
    public R<?> deductStockBatch(@Valid @RequestBody List<DeductStockDTO> dtoList) {
        inventoryService.deductStockBatch(dtoList);
        return R.ok();
    }
}
