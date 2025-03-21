package com.cloud.boot.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.boot.user.model.dto.RoleListQuery;
import com.cloud.boot.user.model.dto.RolePageQuery;
import com.cloud.boot.user.model.dto.SaveRoleDTO;
import com.cloud.boot.user.model.vo.RoleDetailVO;
import com.cloud.boot.user.model.vo.RoleListVO;

import java.util.List;

/**
 * @author lhd
 */
public interface SysRoleService {

    IPage<RoleListVO> getRolePage(RolePageQuery query);

    List<RoleListVO> listRoles(RoleListQuery query);

    RoleDetailVO getRoleById(Long id);

    void saveRole(SaveRoleDTO dto);

    void updateRoleById(Long id, SaveRoleDTO dto);

    void removeRoleById(Long id);
    
}
