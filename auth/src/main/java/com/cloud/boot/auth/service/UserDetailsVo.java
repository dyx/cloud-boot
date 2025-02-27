package com.cloud.boot.auth.service;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author lhd
 */
@Getter
public class UserDetailsVo extends User {

    private final Long id;

    public UserDetailsVo(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
    }
}
