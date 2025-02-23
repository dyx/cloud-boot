package com.cloud.boot.user.feign.fallback;

import com.cloud.boot.user.feign.UserFeignClient;
import org.springframework.stereotype.Component;

/**
 * @author lhd
 */
@Component
public class UserFeignClientFallback implements UserFeignClient {
}
