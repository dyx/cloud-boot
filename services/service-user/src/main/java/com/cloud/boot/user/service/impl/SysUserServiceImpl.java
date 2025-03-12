package com.cloud.boot.user.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.boot.common.core.constant.enums.UserStatusEnum;
import com.cloud.boot.common.core.exception.BizException;
import com.cloud.boot.common.resource.server.util.UserUtil;
import com.cloud.boot.user.mapper.SysUserMapper;
import com.cloud.boot.user.model.converter.SysUserConverter;
import com.cloud.boot.user.model.dto.SaveUserDTO;
import com.cloud.boot.user.model.dto.UpdateUserDTO;
import com.cloud.boot.user.model.dto.UserListQuery;
import com.cloud.boot.user.model.dto.UserPageQuery;
import com.cloud.boot.user.model.entity.SysUserDO;
import com.cloud.boot.user.model.vo.UserAuthVO;
import com.cloud.boot.user.model.vo.UserDetailVO;
import com.cloud.boot.user.model.vo.UserInfoVO;
import com.cloud.boot.user.model.vo.UserListVO;
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
    public UserInfoVO getCurrentUserInfo() {

        Long userId = UserUtil.getUserId();
        SysUserDO dataObj = getById(userId);
        List<Long> roleIdList = sysUserRoleService.getRoleIdListByUserId(userId);
        List<String> permissionList = sysRoleMenuService.getPermissonsByRoleIdList(roleIdList);

        UserInfoVO userInfoVo = SysUserConverter.INSTANCE.do2UserInfoVo(dataObj);
        userInfoVo.setRoleIdList(roleIdList);
        userInfoVo.setPermissionList(permissionList);
        return userInfoVo;
    }

    @Override
    public UserAuthVO getUserAuthInfoByUsername(String username) {

        SysUserDO dataObj = getOne(Wrappers.<SysUserDO>lambdaQuery().eq(SysUserDO::getUsername, username));
        if (dataObj == null) {
            throw new BizException("用户不存在");
        }
        return SysUserConverter.INSTANCE.do2UserAuthVo(dataObj);
    }

    @Override
    public IPage<UserListVO> getUserPage(UserPageQuery query) {

        IPage<SysUserDO> page = page(new Page<>(query.getCurrent(), query.getSize()),
                Wrappers.<SysUserDO>lambdaQuery()
                        .like(StrUtil.isNotBlank(query.getUsername()), SysUserDO::getUsername, query.getUsername())
                        .like(StrUtil.isNotBlank(query.getNickname()), SysUserDO::getNickname, query.getNickname())
                        .eq(StrUtil.isNotBlank(query.getStatus()), SysUserDO::getStatus, query.getStatus())
        );
        IPage<UserListVO> voPage = SysUserConverter.INSTANCE.doPage2VoPage(page);
        return voPage;
    }

    @Override
    public List<UserListVO> listUsers(UserListQuery query) {
        List<SysUserDO> list = list(new Page<>(1, 100),
                Wrappers.<SysUserDO>lambdaQuery()
                        .like(StrUtil.isNotBlank(query.getUsername()), SysUserDO::getUsername, query.getUsername())
                        .like(StrUtil.isNotBlank(query.getNickname()), SysUserDO::getNickname, query.getNickname())
                        .eq(SysUserDO::getStatus, UserStatusEnum.ENABLED.getValue())
        );
        List<UserListVO> voList = SysUserConverter.INSTANCE.doList2VoList(list);
        return voList;
    }

    @Override
    public UserDetailVO getUserDetailById(Long id) {
        UserDetailVO vo = SysUserConverter.INSTANCE.do2detailVo(getById(id));
        return vo;
    }

    @Override
    public void saveUser(SaveUserDTO dto) {
        SysUserDO dataObj = SysUserConverter.INSTANCE.saveDto2Do(dto);
        dataObj.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
        save(dataObj);
    }

    @Override
    public void updateUserById(Long id, UpdateUserDTO dto) {
        SysUserDO dataObj = SysUserConverter.INSTANCE.updateDto2Do(dto);
        dataObj.setId(id);
        updateById(dataObj);
    }

    @Override
    public void removeUserById(Long id) {
        removeById(id);
    }
}
