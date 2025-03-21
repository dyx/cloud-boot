package com.cloud.boot.user.service;

import java.util.List;

/**
 * @author lhd
 */
public interface SysUserRoleService {

    List<Long> getRoleIdListByUserId(Long userId);

    Long countUserByRoleId(Long roleId);
}
