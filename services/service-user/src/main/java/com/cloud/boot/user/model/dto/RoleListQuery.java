package com.cloud.boot.user.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author lhd
 */
@Data
public class RoleListQuery {

    @Schema(description = "角色名称")
    private String name;
}
