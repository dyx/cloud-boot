package com.cloud.boot.common.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lhd
 */
@EnableConfigurationProperties(SwapperProperties.class)
@Configuration
public class SwaggerConfig {

    @Autowired
    private SwapperProperties swapperProperties;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(swapperProperties.getTitle())
                        .version(swapperProperties.getVersion())
                        .description(swapperProperties.getDescription()));
    }


}