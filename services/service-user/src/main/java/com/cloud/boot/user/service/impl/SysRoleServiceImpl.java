package com.cloud.boot.user.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.boot.common.core.exception.BizException;
import com.cloud.boot.common.mybatis.util.PageUtils;
import com.cloud.boot.common.translation.annotaion.EnableTranslation;
import com.cloud.boot.user.mapper.SysRoleMapper;
import com.cloud.boot.user.model.converter.SysRoleConverter;
import com.cloud.boot.user.model.dto.RoleListQuery;
import com.cloud.boot.user.model.dto.RolePageQuery;
import com.cloud.boot.user.model.dto.SaveRoleDTO;
import com.cloud.boot.user.model.entity.SysRoleDO;
import com.cloud.boot.user.model.vo.RoleDetailVO;
import com.cloud.boot.user.model.vo.RoleListVO;
import com.cloud.boot.user.service.SysRoleMenuService;
import com.cloud.boot.user.service.SysRoleService;
import com.cloud.boot.user.service.SysUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lhd
 */
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleDO> implements SysRoleService {

    private final SysUserRoleService sysUserRoleService;
    private final SysRoleMenuService sysRoleMenuService;

    @Override
    public IPage<RoleListVO> getRolePage(RolePageQuery query) {
        IPage<SysRoleDO> page = page(PageUtils.fillPage(query),
                Wrappers.<SysRoleDO>lambdaQuery()
                        .like(StrUtil.isNotBlank(query.getName()), SysRoleDO::getName, query.getName())
        );
        return SysRoleConverter.INSTANCE.doPage2VoPage(page);
    }

    @Override
    public List<RoleListVO> listRoles(RoleListQuery query) {
        List<SysRoleDO> list = list(new Page<>(1, 100),
                Wrappers.<SysRoleDO>lambdaQuery()
                        .like(StrUtil.isNotBlank(query.getName()), SysRoleDO::getName, query.getName())
        );
        return SysRoleConverter.INSTANCE.doList2VoList(list);
    }

    @EnableTranslation
    @Override
    public RoleDetailVO getRoleById(Long id) {
        return SysRoleConverter.INSTANCE.do2DetailVo(getById(id));
    }

    @Override
    public void saveRole(SaveRoleDTO dto) {
        SysRoleDO dataObj = SysRoleConverter.INSTANCE.saveDto2Do(dto);
        save(dataObj);
    }

    @Override
    public void updateRoleById(Long id, SaveRoleDTO dto) {
        SysRoleDO dataObj = SysRoleConverter.INSTANCE.saveDto2Do(dto);
        dataObj.setId(id);
        updateById(dataObj);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeRoleById(Long id) {
        SysRoleDO dataObj = getById(id);
        if (dataObj == null) {
            return;
        }
        if (dataObj.getPreset()) {
            throw new BizException("预置数据不可删除");
        }
        // 该角色已分配给 [N] 个用户，请先解除分配
        Long userCount = sysUserRoleService.countUserByRoleId(id);
        if (userCount != null && userCount > 0) {
            throw new BizException(String.format("该角色已分配给 [%s] 个用户，请先解除分配", userCount));
        }

        sysRoleMenuService.removeRoleMenuByRoleId(id);
        removeById(id);
    }
}
