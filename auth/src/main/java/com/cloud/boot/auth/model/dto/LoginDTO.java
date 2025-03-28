package com.cloud.boot.auth.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author lhd
 */
@Data
public class LoginDTO {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
