package com.cloud.boot.user.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.boot.common.mybatis.base.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lhd
 */
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dict_type")
@Data
public class SysDictTypeDO extends BaseDO {

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;
}