package com.cloud.boot.user.model.vo;

import lombok.Data;

/**
 * @author lhd
 */
@Data
public class UserAuthVo {

    private Long id;
    private String username;
    private String password;
    private String status;
}
