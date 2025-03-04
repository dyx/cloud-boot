package com.cloud.boot.order.feign;

import com.cloud.boot.common.core.constant.ServiceConfig;
import com.cloud.boot.common.core.util.R;
import com.cloud.boot.order.feign.fallback.OrderFeignClientFallback;
import com.cloud.boot.order.model.dto.SaveOrderDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author lhd
 */
@FeignClient(value = ServiceConfig.Order.ID, path = "/order", fallback = OrderFeignClientFallback.class)
public interface OrderFeignClient {

    @PostMapping
    R<?> saveOrder(@Valid @RequestBody SaveOrderDTO dto);
}
