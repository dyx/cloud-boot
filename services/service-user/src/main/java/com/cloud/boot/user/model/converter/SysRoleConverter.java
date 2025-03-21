package com.cloud.boot.user.model.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.boot.user.model.dto.SaveRoleDTO;
import com.cloud.boot.user.model.entity.SysRoleDO;
import com.cloud.boot.user.model.vo.RoleDetailVO;
import com.cloud.boot.user.model.vo.RoleListVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author lhd
 */
@Mapper
public interface SysRoleConverter {

    SysRoleConverter INSTANCE = Mappers.getMapper( SysRoleConverter.class );

    Page<RoleListVO> doPage2VoPage(IPage<SysRoleDO> page);

    List<RoleListVO> doList2VoList(List<SysRoleDO> list);

    RoleDetailVO do2DetailVo(SysRoleDO dataObj);

    SysRoleDO saveDto2Do(SaveRoleDTO dto);
}
