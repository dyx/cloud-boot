package com.cloud.boot.product.feign.fallback;

import com.cloud.boot.common.core.constant.enums.GlobalErrorCodeEnum;
import com.cloud.boot.common.core.util.R;
import com.cloud.boot.product.feign.InventoryFeignClient;
import com.cloud.boot.product.model.dto.DeductStockDTO;

import java.util.List;

/**
 * @author lhd
 */
public class InventoryFeignClientFallback implements InventoryFeignClient {

    @Override
    public R<?> deductStockBatch(List<DeductStockDTO> dtoList) {
        return R.fail(GlobalErrorCodeEnum.UNKNOWN_ERROR);
    }
}
