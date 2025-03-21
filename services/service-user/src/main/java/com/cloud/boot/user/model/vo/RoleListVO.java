package com.cloud.boot.user.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author lhd
 */
@Data
public class RoleListVO {

    @Schema(description = "角色id")
    private Long id;

    @Schema(description = "角色名称")
    private String name;

    @Schema(description = "描述")
    private String description;
}
