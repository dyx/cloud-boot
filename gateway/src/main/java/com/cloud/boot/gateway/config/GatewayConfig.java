package com.cloud.boot.gateway.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import com.cloud.boot.common.core.constant.ServiceConfig;
import com.cloud.boot.common.core.exception.AuthException;
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
                .addExclude("/webjars/**", "/v3/api-docs/**", "/doc.html", "/swagger-ui.html", "/swagger-ui/**", "/swagger-resources", "/favicon.ico")
                .addExclude(ServiceConfig.SERVICES.stream().map(serviceInfo -> serviceInfo.contextPath() + "/v3/api-docs/**").toArray(String[]::new))
                .addExclude("/auth/login")
                .setAuth(obj -> {
                    SaRouter.match("/**", r -> StpUtil.checkLogin());
                })
                .setBeforeAuth(obj -> {
                    SaHolder.getResponse()
                            .setHeader("Access-Control-Allow-Origin", "*")
                            .setHeader("Access-Control-Allow-Methods", "*")
                            .setHeader("Access-Control-Allow-Headers", "*")
                            .setHeader("Access-Control-Max-Age", "3600");
                    SaRouter.match(SaHttpMethod.OPTIONS).back();
                })
                .setError(e -> {
                    throw new AuthException("认证失败：" + e.getMessage());
                });
    }
}
