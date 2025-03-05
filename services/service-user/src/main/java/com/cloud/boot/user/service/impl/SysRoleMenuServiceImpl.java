package com.cloud.boot.user.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.boot.user.mapper.SysRoleMenuMapper;
import com.cloud.boot.user.model.entity.SysRoleMenuDO;
import com.cloud.boot.user.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lhd
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenuDO> implements SysRoleMenuService {

    @Override
    public List<String> getPermissonsByRoleIdList(List<Long> roleIdList) {
        if (CollUtil.isEmpty(roleIdList)) {
            return List.of();
        }
        return baseMapper.selectPermissionByRoleIdList(roleIdList);
    }
}
