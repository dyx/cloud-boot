package com.cloud.boot.common.mybatis.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author lhd
 */
@Data
public class BasePageQuery {

    @Schema(description = "当前页数")
    private Long current;

    @Schema(description = "每页记录数")
    private Long size;

    @Schema(description = "排序字段")
    private String sortField;

    @Schema(description = "排序")
    private String sortOrder;

    public Long getCurrent() {
        if (current == null || current < 1) {
            current = 1L;
        }
        return current;
    }

    public Long getSize() {
        if (size == null || size < 1) {
            size = 10L;
        }
        return size;
    }
}
