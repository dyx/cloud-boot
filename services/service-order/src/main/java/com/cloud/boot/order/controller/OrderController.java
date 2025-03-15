package com.cloud.boot.order.controller;

import com.cloud.boot.common.core.util.R;
import com.cloud.boot.order.model.dto.SaveOrderDTO;
import com.cloud.boot.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lhd
 * <p></p>
 * 订单 视图控制器
 */
@Tag(name = "订单接口")
@RequestMapping("/order")
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "创建订单")
    @PostMapping
    public R<?> saveOrder(@Valid @RequestBody SaveOrderDTO dto) {
        orderService.saveOrder(dto);
        return R.ok();
    }
}
