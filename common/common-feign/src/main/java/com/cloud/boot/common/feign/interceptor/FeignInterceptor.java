package com.cloud.boot.common.feign.interceptor;

import cn.dev33.satoken.stp.StpUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * @author lhd
 */
public class FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(StpUtil.getTokenName(), StpUtil.getTokenValue());
    }
}