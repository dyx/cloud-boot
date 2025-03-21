package com.cloud.boot.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.boot.user.mapper.SysUerRoleMapper;
import com.cloud.boot.user.model.entity.SysUserRoleDO;
import com.cloud.boot.user.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lhd
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUerRoleMapper, SysUserRoleDO> implements SysUserRoleService {

    @Override
    public List<Long> getRoleIdListByUserId(Long userId) {
        if (userId == null) {
            return List.of();
        }
        return list(Wrappers.<SysUserRoleDO>lambdaQuery().eq(SysUserRoleDO::getUserId, userId)).stream().map(SysUserRoleDO::getRoleId).toList();
    }

    @Override
    public Long countUserByRoleId(Long roleId) {
        return count(Wrappers.<SysUserRoleDO>lambdaQuery().eq(SysUserRoleDO::getRoleId, roleId));
    }
}
