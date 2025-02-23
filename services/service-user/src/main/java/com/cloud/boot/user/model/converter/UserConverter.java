package com.cloud.boot.user.model.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lhd
 */
@Mapper
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper( UserConverter.class );
}
