package com.cloud.boot.user.model.vo;


import com.cloud.boot.common.translation.annotaion.Translate;
import com.cloud.boot.common.translation.annotaion.TranslateMapping;
import com.cloud.boot.common.translation.annotaion.TranslateMappingKeyConstant;
import com.cloud.boot.common.translation.annotaion.TranslatorTypeConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author lhd
 */
@Data
public class RoleDetailVO {

    @Schema(description = "用户名")
    private String name;

    @Schema(description = "描述")
    private String description;

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
