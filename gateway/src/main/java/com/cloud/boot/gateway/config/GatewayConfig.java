package com.cloud.boot.gateway.config;

import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import com.cloud.boot.common.core.exception.BizException;
import com.cloud.boot.gateway.handler.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lhd
 */
@Configuration
public class GatewayConfig {

    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

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
}
