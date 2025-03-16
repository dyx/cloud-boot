package com.cloud.boot.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.boot.user.model.entity.SysMenuDO;
import com.cloud.boot.user.model.entity.SysRoleMenuDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lhd
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenuDO> {

    List<SysMenuDO> getMenuListByRoleIdList(@Param("roleIdList") List<Long> roleIdList);

    List<String> getPermissionByRoleIdList(@Param("roleIdList") List<Long> roleIdList);
}
