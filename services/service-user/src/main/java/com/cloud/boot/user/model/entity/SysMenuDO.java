package com.cloud.boot.user.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.boot.common.mybatis.base.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lhd
 */
@EqualsAndHashCode(callSuper = true)
@TableName("sys_menu")
@Data
public class SysMenuDO extends BaseDO {

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单类型，1目录，2菜单，3按钮
     */
    private String type;

    /**
     * 权限标识
     */
    private String permission;

    /**
     * 路由路径
     */
    private String path;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 排序号
     */
    private Integer orderNum;
}