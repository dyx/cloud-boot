package com.cloud.boot.user.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author lhd
 */
@Data
public class SaveRoleDTO {

    @NotBlank
    @Schema(description = "角色名称")
    private String name;

    @Schema(description = "描述")
    private String description;
}
