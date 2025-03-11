package com.cloud.boot.user.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @author lhd
 */
@Data
public class UserCurrentInfoVo {

    private UserAuthVO userAuthVo;
    private List<String> permissionList;
    private List<Long> roleIdList;
}
