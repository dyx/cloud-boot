package com.cloud.boot.common.resource.server.util;

import cn.dev33.satoken.stp.StpUtil;

/**
 * @author lhd
 */
public class UserUtil {

    public static Long getUserId() {
        try {
            return StpUtil.getLoginIdAsLong();
        }
        catch (Exception ignored) {}
        return -1L;
    }
}
