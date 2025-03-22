package com.cloud.boot.user.feign.fallback;

import com.cloud.boot.common.core.constant.enums.GlobalErrorCodeEnum;
import com.cloud.boot.common.core.util.R;
import com.cloud.boot.user.feign.OperationLogFeignClient;
import com.cloud.boot.user.model.dto.SaveOperationLogDTO;
import org.springframework.stereotype.Component;

/**
 * @author lhd
 */
@Component
public class OperationLogClientFallback implements OperationLogFeignClient {

    @Override
    public R<?> saveOperationLog(SaveOperationLogDTO dto) {
        return R.fail(GlobalErrorCodeEnum.REST_SERVICE_UNAVAILABLE);
    }
}
