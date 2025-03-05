package com.cloud.boot.user.model.vo;

import lombok.Data;

/**
 * @author lhd
 */
@Data
public class UserCurrentVo {

    private Long id;
    private String username;
    private String nickname;
    private String email;
    private String phone;
    private String status;
}
