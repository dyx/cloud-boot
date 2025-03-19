package com.cloud.boot.user.feign.fallback;

import com.cloud.boot.common.core.constant.enums.GlobalErrorCodeEnum;
import com.cloud.boot.common.core.util.R;
import com.cloud.boot.user.feign.DictFeignClient;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author lhd
 */
@Component
public class DictFeignClientFallback implements DictFeignClient {

    @Override
    public R<Map<String, String>> getDictMapByCodeForTranslator(String code) {
        return R.fail(GlobalErrorCodeEnum.REST_SERVICE_UNAVAILABLE);
    }
}
