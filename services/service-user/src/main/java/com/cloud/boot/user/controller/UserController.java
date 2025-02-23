package com.cloud.boot.user.controller;

import com.cloud.boot.common.core.util.RHandler;
import com.cloud.boot.order.feign.OrderFeignClient;
import com.cloud.boot.order.model.dto.SaveOrderDTO;
import com.cloud.boot.order.model.dto.SaveOrderItemDTO;
import com.cloud.boot.product.feign.InventoryFeignClient;
import com.cloud.boot.product.model.dto.DeductStockDTO;
import com.cloud.boot.common.core.util.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lhd
 */
@Tag(name = "用户接口")
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private InventoryFeignClient inventoryFeignClient;
    @Autowired
    private OrderFeignClient orderFeignClient;

    @Operation(summary = "采购商品")
    @GlobalTransactional
    @PostMapping("purchase")
    public R<?> purchase(@Valid @RequestBody SaveOrderDTO dto) {

        List<DeductStockDTO> deductStockDTOList = new ArrayList<>();
        for (SaveOrderItemDTO saveOrderItemDTO : dto.getOrderItemList()) {
            DeductStockDTO deductStockDTO = new DeductStockDTO();
            deductStockDTO.setProductId(saveOrderItemDTO.getProductId());
            deductStockDTO.setStock(saveOrderItemDTO.getQuantity());
            deductStockDTOList.add(deductStockDTO);
        }

        RHandler.of(orderFeignClient.saveOrder(dto)).failThenThrow();

        RHandler.of(inventoryFeignClient.deductStockBatch(deductStockDTOList)).failThenThrow();

        return R.ok();
    }
}
