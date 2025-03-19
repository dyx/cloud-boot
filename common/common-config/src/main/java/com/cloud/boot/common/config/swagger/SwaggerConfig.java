package com.cloud.boot.common.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lhd
 */
@EnableConfigurationProperties(SwapperProperties.class)
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    private final SwapperProperties swapperProperties;

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title(swapperProperties.getTitle())
                        .version(swapperProperties.getVersion())
                        .description(swapperProperties.getDescription()));
    }


}