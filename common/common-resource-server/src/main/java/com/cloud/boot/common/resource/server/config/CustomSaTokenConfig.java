package com.cloud.boot.common.resource.server.config;

import cn.dev33.satoken.config.SaTokenConfig;
import cn.dev33.satoken.interceptor.SaInterceptor;
import com.cloud.boot.common.core.constant.CommonConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lhd
 */
@Configuration
public class CustomSaTokenConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 打开注解鉴权
        registry.addInterceptor(new SaInterceptor()).addPathPatterns("/**");
    }

    @Bean
    @Primary
    public SaTokenConfig getSaTokenConfigPrimary() {
        SaTokenConfig config = new SaTokenConfig();
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