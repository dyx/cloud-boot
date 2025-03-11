package com.cloud.boot.user.model.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.boot.user.model.dto.SaveUserDTO;
import com.cloud.boot.user.model.dto.UpdateUserDTO;
import com.cloud.boot.user.model.entity.SysUserDO;
import com.cloud.boot.user.model.vo.UserAuthVO;
import com.cloud.boot.user.model.vo.UserDetailVO;
import com.cloud.boot.user.model.vo.UserInfoVO;
import com.cloud.boot.user.model.vo.UserListVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author lhd
 */
@Mapper
public interface SysUserConverter {

    SysUserConverter INSTANCE = Mappers.getMapper( SysUserConverter.class );

    UserInfoVO do2UserInfoVo(SysUserDO dataObj);

    UserAuthVO do2UserAuthVo(SysUserDO dataObj);

    Page<UserListVO> doPage2VoPage(IPage<SysUserDO> page);

    List<UserListVO> doList2VoList(List<SysUserDO> list);

    UserDetailVO do2detailVo(SysUserDO dataObj);

    SysUserDO saveDto2Do(SaveUserDTO dto);

    SysUserDO updateDto2Do(UpdateUserDTO dto);
}
