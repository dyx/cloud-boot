package com.cloud.boot.common.translation.feign;

import com.cloud.boot.common.core.constant.ServiceConfig;
import com.cloud.boot.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.Set;

/**
 * @author lhd
 */
@FeignClient(value = ServiceConfig.User.ID, contextId = "userServiceFeignClient")
public interface UserServiceFeignClient {

    @PostMapping("/dict/translate/batch")
    R<Map<String, Map<String, Object>>> batchTranslateDict(@RequestBody Set<String> sourceValueSet);

    @PostMapping("/user/translate/batch")
    R<Map<Long, Map<String, Object>>> batchTranslateUser(@RequestBody Set<Long> sourceValueSet);
}
