package com.cloud.boot.common.config;

import com.cloud.boot.common.config.aspect.HttpLogAspect;
import com.cloud.boot.common.config.handler.GlobalExceptionHandler;
import com.cloud.boot.common.config.sentinel.CustomBlockExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lhd
 */
@Configuration(proxyBeanMethods = false)
public class ConfigAutoConfiguration {
    @Bean
    public HttpLogAspect httpLogAop() {
        return new HttpLogAspect();
    }

    @Bean
    public CustomBlockExceptionHandler blockHandler() {
        return new CustomBlockExceptionHandler();
    }

    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }
}
