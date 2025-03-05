package com.cloud.boot.user.model.converter;

import com.cloud.boot.user.model.dto.SaveUserDTO;
import com.cloud.boot.user.model.entity.SysUserDO;
import com.cloud.boot.user.model.vo.UserAuthVo;
import com.cloud.boot.user.model.vo.UserInfoVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lhd
 */
@Mapper
public interface SysUserConverter {

    SysUserConverter INSTANCE = Mappers.getMapper( SysUserConverter.class );

    UserInfoVo do2UserInfoVo(SysUserDO dataObj);

    UserAuthVo do2UserAuthVo(SysUserDO dataObj);

    SysUserDO saveUserDTOtoDO(SaveUserDTO dto);
}
