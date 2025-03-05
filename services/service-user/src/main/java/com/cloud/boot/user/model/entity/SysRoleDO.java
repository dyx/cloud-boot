package com.cloud.boot.user.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.boot.common.mybatis.base.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lhd
 */
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
@Data
public class SysRoleDO extends BaseDO {

    /**
     * 角色名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;
}