package com.cloud.boot.user.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.boot.user.mapper.SysRoleMenuMapper;
import com.cloud.boot.user.model.converter.SysMenuConverter;
import com.cloud.boot.user.model.entity.SysRoleMenuDO;
import com.cloud.boot.user.model.vo.MenuTreeVO;
import com.cloud.boot.user.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * @author lhd
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenuDO> implements SysRoleMenuService {

    @Override
    public List<MenuTreeVO> getMenuTreeByRoleIdList(List<Long> roleIdList) {
        if (CollUtil.isEmpty(roleIdList)) {
            return List.of();
        }
        List<MenuTreeVO> voList = SysMenuConverter.INSTANCE.doList2TreeVoList(baseMapper.getMenuListByRoleIdList(roleIdList));
        return buildMenuTree(0L, voList);
    }

    @Override
    public List<String> getPermissonsByRoleIdList(List<Long> roleIdList) {
        if (CollUtil.isEmpty(roleIdList)) {
            return List.of();
        }
        return baseMapper.getPermissionByRoleIdList(roleIdList);
    }

    private List<MenuTreeVO> buildMenuTree(Long parentId, List<MenuTreeVO> sourceList) {

        return sourceList.stream()
                .filter(menu -> parentId.equals(menu.getParentId()))
                .peek(menu -> {
                    List<MenuTreeVO> children = buildMenuTree(menu.getId(), sourceList);
                    menu.setChildren(children.isEmpty() ? null : children);
                })
                .sorted(Comparator.comparingInt(MenuTreeVO::getOrderNum))
                .toList();
    }
}
