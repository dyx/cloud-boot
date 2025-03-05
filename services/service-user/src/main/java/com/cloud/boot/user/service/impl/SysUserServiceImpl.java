package com.cloud.boot.user.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.boot.common.core.exception.BizException;
import com.cloud.boot.common.resource.server.util.UserUtil;
import com.cloud.boot.user.mapper.SysUserMapper;
import com.cloud.boot.user.model.converter.SysUserConverter;
import com.cloud.boot.user.model.dto.SaveUserDTO;
import com.cloud.boot.user.model.entity.SysUserDO;
import com.cloud.boot.user.model.vo.UserInfoVo;
import com.cloud.boot.user.model.vo.UserAuthVo;
import com.cloud.boot.user.service.SysRoleMenuService;
import com.cloud.boot.user.service.SysUserRoleService;
import com.cloud.boot.user.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lhd
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserDO> implements SysUserService {

    private final SysUserRoleService sysUserRoleService;
    private final SysRoleMenuService sysRoleMenuService;

    @Override
    public UserInfoVo getCurrentUserInfo() {

        Long userId = UserUtil.getUserId();
        SysUserDO dataObj = getById(userId);
        List<Long> roleIdList = sysUserRoleService.getRoleIdListByUserId(userId);
        List<String> permissionList = sysRoleMenuService.getPermissonsByRoleIdList(roleIdList);

        UserInfoVo userInfoVo = SysUserConverter.INSTANCE.do2UserInfoVo(dataObj);
        userInfoVo.setRoleIdList(roleIdList);
        userInfoVo.setPermissionList(permissionList);
        return userInfoVo;
    }

    @Override
    public UserAuthVo getUserAuthInfoByUsername(String username) {

        SysUserDO dataObj = getOne(Wrappers.<SysUserDO>lambdaQuery().eq(SysUserDO::getUsername, username));
        if (dataObj == null) {
            throw new BizException("用户不存在");
        }
        return SysUserConverter.INSTANCE.do2UserAuthVo(dataObj);
    }

    @Override
    public void saveUser(SaveUserDTO dto) {
        SysUserDO dataObj = SysUserConverter.INSTANCE.saveUserDTOtoDO(dto);
        dataObj.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
        save(dataObj);
    }
}
