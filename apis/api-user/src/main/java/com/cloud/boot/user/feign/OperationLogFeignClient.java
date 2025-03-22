package com.cloud.boot.user.feign;

import com.cloud.boot.common.core.constant.ServiceConfig;
import com.cloud.boot.common.core.util.R;
import com.cloud.boot.user.feign.fallback.OperationLogClientFallback;
import com.cloud.boot.user.model.dto.SaveOperationLogDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author lhd
 */
@FeignClient(value = ServiceConfig.User.ID, contextId = "operationLogFeignClient", path = "/operation-log", fallback = OperationLogClientFallback.class)
public interface OperationLogFeignClient {

    @PostMapping
    R<?> saveOperationLog(@RequestBody SaveOperationLogDTO dto);
}
