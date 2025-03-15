package com.cloud.boot.order.feign.fallback;

import com.cloud.boot.common.core.constant.enums.GlobalErrorCodeEnum;
import com.cloud.boot.common.core.util.R;
import com.cloud.boot.order.feign.OrderFeignClient;
import com.cloud.boot.order.model.dto.SaveOrderDTO;

/**
 * @author lhd
 */
public class OrderFeignClientFallback implements OrderFeignClient {

    @Override
    public R<?> saveOrder(SaveOrderDTO dto) {
        return R.fail(GlobalErrorCodeEnum.REST_SERVICE_UNAVAILABLE);
    }
}
