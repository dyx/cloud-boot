package com.cloud.boot.product.feign;

import com.cloud.boot.common.core.constant.ServiceNameConstant;
import com.cloud.boot.common.core.util.R;
import com.cloud.boot.product.feign.fallback.InventoryFeignClientFallback;
import com.cloud.boot.product.model.dto.DeductStockDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author lhd
 */
@FeignClient(value = ServiceNameConstant.SERVICE_PRODUCT, path = "/inventory", fallback = InventoryFeignClientFallback.class)
public interface InventoryFeignClient {

    @PostMapping("/stock/deduct/batch")
    R<?> deductStockBatch(@RequestBody List<DeductStockDTO> dtoList);
}
