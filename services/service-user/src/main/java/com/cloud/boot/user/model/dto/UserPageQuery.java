package com.cloud.boot.user.model.dto;

import com.cloud.boot.common.mybatis.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lhd
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserPageQuery extends BasePageQuery {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "用户状态")
    private String status;
}
