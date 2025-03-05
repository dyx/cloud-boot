package com.cloud.boot.user.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author lhd
 */
@Data
public class SaveUserDTO {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String nickname;
    private String email;
    private String phone;
}
