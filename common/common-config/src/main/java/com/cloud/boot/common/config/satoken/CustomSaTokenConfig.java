package com.cloud.boot.common.config.satoken;

import cn.dev33.satoken.config.SaTokenConfig;
import com.cloud.boot.common.core.constant.CommonConstant;
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
