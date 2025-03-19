package com.cloud.boot.user.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.boot.common.mybatis.base.BaseDO;
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
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 用户状态
     */
    private String status;
}