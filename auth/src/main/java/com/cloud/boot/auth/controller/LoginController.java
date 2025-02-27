package com.cloud.boot.auth.controller;

import com.cloud.boot.auth.util.JwtUtil;
import com.cloud.boot.auth.util.SecurityUtil;
import com.cloud.boot.common.core.util.R;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lhd
 */
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("login")
    public R<String> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return R.ok(jwtUtil.generateToken(SecurityUtil.getUser(authentication)));
    }
}
