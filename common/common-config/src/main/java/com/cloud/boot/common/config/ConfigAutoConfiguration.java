package com.cloud.boot.common.config;

import com.cloud.boot.common.config.aop.HttpLogAop;
import com.cloud.boot.common.config.sentinel.CustomBlockExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lhd
 */
@Configuration(proxyBeanMethods = false)
public class ConfigAutoConfiguration {
    @Bean
    public HttpLogAop httpLogAop() {
        return new HttpLogAop();
    }

    @Bean
    public CustomBlockExceptionHandler blockHandler() {
        return new CustomBlockExceptionHandler();
    }
}
