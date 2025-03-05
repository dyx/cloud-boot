package com.cloud.boot.user.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.boot.common.core.exception.BizException;
import com.cloud.boot.user.mapper.UserMapper;
import com.cloud.boot.user.model.converter.UserConverter;
import com.cloud.boot.user.model.dto.SaveUserDTO;
import com.cloud.boot.user.model.entity.UserDO;
import com.cloud.boot.user.model.vo.UserInfoVo;
import com.cloud.boot.user.model.vo.UserVo;
import com.cloud.boot.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author lhd
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    @Override
    public UserInfoVo getUserInfoByUsername(String username) {

        UserDO dataObj = getOne(Wrappers.<UserDO>lambdaQuery().eq(UserDO::getUsername, username));
        if (dataObj == null) {
            throw new BizException("用户不存在");
        }
        UserVo userVo = UserConverter.INSTANCE.do2UserVo(dataObj);

        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setUserVo(userVo);
        return userInfoVo;
    }

    @Override
    public void saveUser(SaveUserDTO dto) {
        UserDO dataObj = UserConverter.INSTANCE.saveUserDTOtoDO(dto);
        dataObj.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
        save(dataObj);
    }
}
