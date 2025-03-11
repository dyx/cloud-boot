package com.cloud.boot.user.feign.fallback;

import com.cloud.boot.common.core.constant.enums.GlobalErrorCodeEnum;
import com.cloud.boot.common.core.util.R;
import com.cloud.boot.user.feign.UserFeignClient;
import com.cloud.boot.user.model.vo.UserAuthVO;
import org.springframework.stereotype.Component;

/**
 * @author lhd
 */
@Component
public class UserFeignClientFallback implements UserFeignClient {

    @Override
    public R<UserAuthVO> getUserAuthInfoByUsername(String username) {
        return R.fail(GlobalErrorCodeEnum.REST_SERVICE_UNAVAILABLE);
    }
}
