package com.cloud.boot.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.boot.user.model.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
}
