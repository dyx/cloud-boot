package com.cloud.boot.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.boot.user.mapper.UserMapper;
import com.cloud.boot.user.model.entity.UserDO;
import com.cloud.boot.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author lhd
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

}
