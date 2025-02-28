package com.cloud.boot.auth.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lhd
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return CollUtil.newArrayList("test");
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return CollUtil.newArrayList("admin");
    }
}
