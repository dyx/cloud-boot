package com.cloud.boot.user.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author lhd
 */
@Data
public class SaveUserDTO {

    @NotBlank
    @Schema(description = "用户名")
    private String username;

    @NotBlank
    @Schema(description = "密码")
    private String password;

    @NotBlank
    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "用户邮箱")
    private String email;

    @Schema(description = "手机号")
    private String phone;

    @NotBlank
    @Schema(description = "状态")
    private String status;
}
