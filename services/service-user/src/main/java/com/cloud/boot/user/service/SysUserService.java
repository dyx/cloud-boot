package com.cloud.boot.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.boot.user.model.dto.UserListQuery;
import com.cloud.boot.user.model.dto.UserPageQuery;
import com.cloud.boot.user.model.dto.SaveUserDTO;
import com.cloud.boot.user.model.dto.UpdateUserDTO;
import com.cloud.boot.user.model.vo.UserAuthVO;
import com.cloud.boot.user.model.vo.UserDetailVO;
import com.cloud.boot.user.model.vo.UserInfoVO;
import com.cloud.boot.user.model.vo.UserListVO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lhd
 */
public interface SysUserService {

    UserInfoVO getCurrentUserInfo();

    UserAuthVO getUserAuthInfoByUsername(String username);

    IPage<UserListVO> getUserPage(UserPageQuery query);

    List<UserListVO> listUsers(UserListQuery query);

    UserDetailVO getUserById(Long id);

    Map<Long, Map<String, Object>> batchTranslateUser(Set<Long> sourceValueSet);

    void saveUser(SaveUserDTO dto);

    void updateUserById(Long id, UpdateUserDTO dto);

    void removeUserById(Long id);
}
