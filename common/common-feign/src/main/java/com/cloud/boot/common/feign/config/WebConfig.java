package com.cloud.boot.common.feign.config;

import com.cloud.boot.common.feign.converter.StringToDateConverter;
import com.cloud.boot.common.feign.converter.StringToLocalDateTimeConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lhd
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToDateConverter());
        registry.addConverter(new StringToLocalDateTimeConverter());
    }
}