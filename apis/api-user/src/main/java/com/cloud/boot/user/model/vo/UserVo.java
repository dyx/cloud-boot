package com.cloud.boot.user.model.vo;

import lombok.Data;

/**
 * @author lhd
 */
@Data
public class UserVo {

    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private Integer status;
}
