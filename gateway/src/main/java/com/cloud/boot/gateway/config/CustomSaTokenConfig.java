package com.cloud.boot.gateway.config;

import cn.dev33.satoken.config.SaTokenConfig;
import com.cloud.boot.common.core.constant.SaTokenConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author lhd
 */
@Configuration
public class CustomSaTokenConfig {

    @Bean
    @Primary
    public SaTokenConfig getSaTokenConfigPrimary() {

        SaTokenConfig config = new SaTokenConfig();
        config.setTokenName(SaTokenConstant.SA_TOKEN_TOKEN_NAME);
        config.setTokenStyle(SaTokenConstant.SA_TOKEN_TOKEN_STYLE);
        config.setTimeout(SaTokenConstant.SA_TOKEN_TIME_OUT);
        config.setActiveTimeout(SaTokenConstant.SA_TOKEN_ACTIVE_TIMEOUT);
        config.setIsConcurrent(SaTokenConstant.SA_TOKEN_IS_CONCURRENT);
        config.setIsShare(SaTokenConstant.SA_TOKEN_IS_SHARE);
        config.setIsLog(SaTokenConstant.SA_TOKEN_IS_LOG);
        config.setIsReadCookie(SaTokenConstant.SA_TOKEN_IS_READ_COOKIE);
        config.setIsReadBody(SaTokenConstant.SA_TOKEN_IS_READ_BODY);
        config.setIsReadHeader(SaTokenConstant.SA_TOKEN_IS_READ_HEADER);
        return config;
    }
}