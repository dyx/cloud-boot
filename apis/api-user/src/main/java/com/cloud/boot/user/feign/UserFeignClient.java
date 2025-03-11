package com.cloud.boot.user.feign;

import com.cloud.boot.common.core.constant.ServiceConfig;
import com.cloud.boot.common.core.util.R;
import com.cloud.boot.user.feign.fallback.UserFeignClientFallback;
import com.cloud.boot.user.model.vo.UserAuthVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author lhd
 */
@FeignClient(value = ServiceConfig.User.ID, path = "/user", fallback = UserFeignClientFallback.class)
public interface UserFeignClient {

    @GetMapping("/username/{username}")
    R<UserAuthVO> getUserAuthInfoByUsername(@PathVariable("username") String username);
}
