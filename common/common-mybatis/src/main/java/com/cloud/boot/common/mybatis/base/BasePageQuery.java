package com.cloud.boot.common.mybatis.base;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author lhd
 */
@Data
public class BasePageQuery {

    @NotNull
    @Schema(description = "当前页数")
    private Long current;

    @NotNull
    @Schema(description = "每页记录数")
    private Long size;
}
