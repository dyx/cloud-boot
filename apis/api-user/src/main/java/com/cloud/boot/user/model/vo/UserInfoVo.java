package com.cloud.boot.user.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @author lhd
 */
@Data
public class UserInfoVo {

    private UserVo userVo;
    private List<String> permissionList;
    private List<Long> roleIdList;
}
