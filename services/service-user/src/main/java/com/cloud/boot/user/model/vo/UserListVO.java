package com.cloud.boot.user.model.vo;

import com.cloud.boot.common.translation.annotaion.TranslateMapping;
import com.cloud.boot.common.translation.annotaion.TranslateMappingKeyConstant;
import com.cloud.boot.common.translation.annotaion.Translate;
import com.cloud.boot.common.translation.annotaion.TranslatorTypeEnum;
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

    @Translate(type = TranslatorTypeEnum.DICT, dictCode = "user_status")
    private String status;
    private String statusName;

    @Translate(type = TranslatorTypeEnum.USER, target = {
            @TranslateMapping(mappingKey = TranslateMappingKeyConstant.USER_NICKNAME, target = "createByName"),
            @TranslateMapping(mappingKey = TranslateMappingKeyConstant.USER_USERNAME, target = "createByUsername"),
            @TranslateMapping(mappingKey = TranslateMappingKeyConstant.USER_PHONE, target = "phone")
    })
    private Long createBy;
    private String createByName;
    private String createByUsername;

    @Translate(type = TranslatorTypeEnum.USER, target = @TranslateMapping(mappingKey = TranslateMappingKeyConstant.USER_NICKNAME, target = "updateByName"))
    private Long updateBy;
    private String updateByName;
}
