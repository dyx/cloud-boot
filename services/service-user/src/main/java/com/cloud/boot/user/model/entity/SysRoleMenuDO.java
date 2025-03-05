package com.cloud.boot.user.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author lhd
 */
@TableName("sys_role_menu")
@Data
public class SysRoleMenuDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单权限ID
     */
    private Long menuId;
}