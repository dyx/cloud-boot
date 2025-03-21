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
public class RolePageQuery extends BasePageQuery {

    @Schema(description = "角色名称")
    private String name;
}
