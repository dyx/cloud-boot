package com.cloud.boot.user.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lhd
 */
@TableName("sys_menu")
@Data
public class SysMenuDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;

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

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long updateBy;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 删除时间
     */
    @TableLogic
    private LocalDateTime deleteTime;
}