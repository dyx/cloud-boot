package com.cloud.boot.user.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @author lhd
 */
@Data
public class UserInfoVO {

    private Long id;
    private String username;
    private String nickname;
    private List<Long> roleIdList;
    private List<MenuTreeVO> menuList;
    private List<String> permissionList;
}
