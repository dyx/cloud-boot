package com.cloud.boot.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.boot.user.model.entity.SysUserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lhd
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserDO> {
}
