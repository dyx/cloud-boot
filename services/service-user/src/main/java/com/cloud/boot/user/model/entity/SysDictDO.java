package com.cloud.boot.user.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cloud.boot.common.mybatis.base.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lhd
 */
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dict")
@Data
public class SysDictDO extends BaseDO {

    /**
     * 字典类型ID
     */
    private Long dictTypeId;

    /**
     * 编码
     */
    private String code;

    /**
     * 值
     */
    private String value;

    /**
     * 标签
     */
    private String label;

    /**
     * 排序号
     */
    private Integer orderNum;

    /**
     * 描述
     */
    private String description;
}