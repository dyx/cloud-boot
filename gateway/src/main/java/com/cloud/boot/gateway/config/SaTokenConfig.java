package com.cloud.boot.gateway.config;

import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import com.cloud.boot.common.core.constant.CommonConstant;
import com.cloud.boot.common.core.exception.BizException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author lhd
 */
@Configuration
public class SaTokenConfig {

    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
                .addInclude("/**")
                .addExclude("/webjars/**", "/v3/api-docs/**", "/doc.html", "/swagger-ui.html", "/swagger-ui/**", "/swagger-resources","/favicon.ico")
                .addExclude("/auth/login")
                .setAuth(obj -> {
                    SaRouter.match("/**", r -> StpUtil.checkLogin());
                })
                .setError(e -> {
                    throw new BizException("认证失败：" + e.getMessage());
                });
    }

    @Bean
    @Primary
    public cn.dev33.satoken.config.SaTokenConfig getSaTokenConfigPrimary() {
        cn.dev33.satoken.config.SaTokenConfig config = new cn.dev33.satoken.config.SaTokenConfig();
        config.setTokenName(CommonConstant.REQUEST_HEADER_TOKEN_NAME);
        config.setTimeout(24*3600);
        config.setActiveTimeout(-1);
        config.setIsConcurrent(true);
        config.setIsShare(true);
        config.setTokenStyle("simple-uuid");
        config.setIsLog(true);
        config.setIsReadCookie(false);
        config.setIsReadBody(false);
        config.setIsReadHeader(true);
        return config;
    }
}