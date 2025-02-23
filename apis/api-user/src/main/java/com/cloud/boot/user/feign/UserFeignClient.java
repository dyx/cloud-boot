package com.cloud.boot.user.feign;

import com.cloud.boot.common.core.constant.ServiceNameConstant;
import com.cloud.boot.user.feign.fallback.UserFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author lhd
 */
@FeignClient(value = ServiceNameConstant.SERVICE_USER, path = "/user", fallback = UserFeignClientFallback.class)
public interface UserFeignClient {
}
