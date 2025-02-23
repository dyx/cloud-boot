package com.cloud.boot.order.controller;

import com.cloud.boot.order.model.dto.SaveOrderDTO;
import com.cloud.boot.order.model.vo.OrderVO;
import com.cloud.boot.order.service.OrderService;
import com.cloud.boot.common.core.util.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lhd
 * <p></p>
 * 订单 视图控制器
 */
@Tag(name = "订单接口")
@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Operation(summary = "创建订单")
    @PostMapping
    public R<?> saveOrder(@Valid @RequestBody SaveOrderDTO dto) {
        orderService.saveOrder(dto);
        return R.ok();
    }
}
