package com.cloud.boot.common.resource.server;

import com.cloud.boot.common.resource.server.config.CustomSaTokenConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lhd
 */
@Configuration(proxyBeanMethods = false)
public class ResourceServerAutoConfiguration {

    @Bean
    public CustomSaTokenConfig customSaConfig() {
        return new CustomSaTokenConfig();
    }
}
