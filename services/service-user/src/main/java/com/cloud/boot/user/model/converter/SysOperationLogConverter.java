package com.cloud.boot.user.model.converter;

import com.cloud.boot.user.model.dto.SaveOperationLogDTO;
import com.cloud.boot.user.model.entity.SysOperationLogDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author lhd
 */
@Mapper
public interface SysOperationLogConverter {

    SysOperationLogConverter INSTANCE = Mappers.getMapper( SysOperationLogConverter.class );

    SysOperationLogDO saveDto2Do(SaveOperationLogDTO dto);
}
