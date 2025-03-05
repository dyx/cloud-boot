package com.cloud.boot.user.service;

import java.util.List;

/**
 * @author lhd
 */
public interface SysRoleMenuService {

    List<String> getPermissonsByRoleIdList(List<Long> roleIdList);
}
