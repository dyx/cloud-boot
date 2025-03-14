package com.cloud.boot.common.config.web;

import com.cloud.boot.common.config.converter.StringToDateConverter;
import com.cloud.boot.common.config.converter.StringToLocalDateTimeConverter;
import com.cloud.boot.common.config.converter.StringToLongConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lhd
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToLongConverter());
        registry.addConverter(new StringToDateConverter());
        registry.addConverter(new StringToLocalDateTimeConverter());
    }
}