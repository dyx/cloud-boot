package com.cloud.boot.common.feign.jackson;

import com.cloud.boot.common.core.constant.CommonConstant;
import com.cloud.boot.common.core.util.LocalDateTimeModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lhd
 */
@Configuration
public class JacksonConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {

            builder.simpleDateFormat(CommonConstant.NORMAL_DATETIME_FORMATTER);
            builder.serializerByType(Long.class, ToStringSerializer.instance);
            builder.modules(new LocalDateTimeModule());
        };
    }
}