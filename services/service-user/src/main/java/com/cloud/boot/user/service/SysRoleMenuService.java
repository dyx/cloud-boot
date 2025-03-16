package com.cloud.boot.user.service;

import com.cloud.boot.user.model.vo.MenuTreeVO;

import java.util.List;

/**
 * @author lhd
 */
public interface SysRoleMenuService {

    List<MenuTreeVO> getMenuTreeByRoleIdList(List<Long> roleIdList);

    List<String> getPermissonsByRoleIdList(List<Long> roleIdList);
}
