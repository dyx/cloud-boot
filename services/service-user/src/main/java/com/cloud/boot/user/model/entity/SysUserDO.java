package com.cloud.boot.user.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.boot.common.mybatis.annotation.Sensitive;
import com.cloud.boot.common.mybatis.base.BaseDO;
import com.cloud.boot.common.mybatis.constant.SensitiveStrategy;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lhd
 */
@TableName("sys_user")
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserDO extends BaseDO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户邮箱
     */
    @Sensitive(strategy = SensitiveStrategy.EMAIL)
    private String email;

    /**
     * 手机号
     */
    @Sensitive(strategy = SensitiveStrategy.PHONE)
    private String phone;

    /**
     * 用户状态
     */
    private String status;

    /**
     * 是否预置数据
     */
    @TableField(value = "is_preset")
    private Boolean preset;
}