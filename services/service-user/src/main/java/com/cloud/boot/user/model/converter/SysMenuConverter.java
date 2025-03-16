package com.cloud.boot.user.model.converter;

import com.cloud.boot.user.model.entity.SysMenuDO;
import com.cloud.boot.user.model.vo.MenuTreeVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author lhd
 */
@Mapper
public interface SysMenuConverter {

    SysMenuConverter INSTANCE = Mappers.getMapper( SysMenuConverter.class );

    List<MenuTreeVO> doList2TreeVoList(List<SysMenuDO> doList);
}
