package com.cloud.boot.user.model.converter;

import com.cloud.boot.user.model.dto.SaveUserDTO;
import com.cloud.boot.user.model.entity.UserDO;
import com.cloud.boot.user.model.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lhd
 */
@Mapper
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper( UserConverter.class );

    UserVo do2UserVo(UserDO dataObj);

    UserDO saveUserDTOtoDO(SaveUserDTO dto);
}
