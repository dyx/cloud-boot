package com.cloud.boot.common.resource.server.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import com.cloud.boot.common.core.util.RHandler;
import com.cloud.boot.user.feign.UserFeignClient;
import com.cloud.boot.user.model.vo.UserInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lhd
 */
@Service
@RequiredArgsConstructor
public class StpInterfaceImpl implements StpInterface {

    private final UserFeignClient userFeignClient;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        UserInfoVO userInfoVO = RHandler.of(userFeignClient.getCurrentUserInfo()).getData();
        return userInfoVO != null && userInfoVO.getPermissionList() != null
                ? userInfoVO.getPermissionList()
                : new ArrayList<>();
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        UserInfoVO userInfoVO = RHandler.of(userFeignClient.getCurrentUserInfo()).getData();
        return userInfoVO != null && userInfoVO.getRoleIdList() != null
                ? userInfoVO.getRoleIdList().stream().map(String::valueOf).toList()
                : new ArrayList<>();
    }

}