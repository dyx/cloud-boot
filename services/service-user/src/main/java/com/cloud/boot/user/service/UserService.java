package com.cloud.boot.user.service;

import com.cloud.boot.user.model.dto.SaveUserDTO;
import com.cloud.boot.user.model.vo.UserInfoVo;

/**
 * @author lhd
 */
public interface UserService {

    UserInfoVo getUserInfoByUsername(String username);

    void saveUser(SaveUserDTO dto);
}
