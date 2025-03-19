package com.cloud.boot.user.model.vo;


import com.cloud.boot.common.translation.annotaion.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author lhd
 */
@Data
public class UserDetailVO {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "用户邮箱")
    private String email;

    @Schema(description = "手机号")
    private String phone;

    @Translate(type = TranslatorTypeConstant.DICT, dictCode = "user_status")
    @Schema(description = "用户状态")
    private String status;
    @Schema(description = "用户状态名称")
    private String statusName;

    @Translate(type = TranslatorTypeConstant.USER,
            target = @TranslateMapping(mappingKey = TranslateMappingKeyConstant.USER_NAME, target = "createByName"))
    @Schema(description = "创建人")
    private Long createBy;
    @Schema(description = "创建人名称")
    private String createByName;

    @Translate(type = TranslatorTypeConstant.USER,
            target = @TranslateMapping(mappingKey = TranslateMappingKeyConstant.USER_NAME, target = "updateByName"))
    @Schema(description = "修改人")
    private Long updateBy;
    @Schema(description = "修改人名称")
    private String updateByName;
}
