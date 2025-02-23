package com.cloud.boot.common.feign.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lhd
 */
@Data
@ConfigurationProperties("swagger")
public class SwapperProperties {

    private String title = "";
    private String version = "";
    private String description = "";
}
