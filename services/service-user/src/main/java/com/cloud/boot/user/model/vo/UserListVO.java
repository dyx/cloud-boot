package com.cloud.boot.user.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author lhd
 */
@Data
public class UserListVO {

    @Schema(description = "用户id")
    private String id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "用户邮箱")
    private String email;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "用户状态")
    private String status;

    @Schema(description = "用户状态标签")
    private String statusLabel;
}
