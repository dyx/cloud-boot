package com.cloud.boot.user.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author lhd
 */
@Data
public class UpdateUserDTO {

    @NotBlank
    @Schema(description = "用户名")
    private String username;

    @NotBlank
    @Schema(description = "用户昵称")
    private String nickname;

    @NotBlank
    @Schema(description = "姓名")
    private String name;

    @Schema(description = "用户邮箱")
    private String email;

    @Schema(description = "手机号")
    private String phone;

    @NotBlank
    @Schema(description = "用户状态")
    private String status;
}
