package com.cloud.boot.user.feign;

import com.cloud.boot.common.core.constant.ServiceConfig;
import com.cloud.boot.user.feign.fallback.UserFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author lhd
 */
@FeignClient(value = ServiceConfig.User.ID, path = "/user", fallback = UserFeignClientFallback.class)
public interface UserFeignClient {
}
