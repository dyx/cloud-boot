package com.cloud.boot.auth.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.cloud.boot.auth.model.dto.LoginDTO;
import com.cloud.boot.common.core.exception.BizException;
import com.cloud.boot.common.core.util.R;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lhd
 */
@RestController
@RequiredArgsConstructor
public class LoginController {

    @PostMapping("login")
    public R<SaTokenInfo> login(@Valid @RequestBody LoginDTO loginDTO) {

        if (!"admin".equals(loginDTO.getUsername()) || !"123456".equals(loginDTO.getPassword())) {
            throw new BizException("用户名或密码错误");
        }

        StpUtil.login(1L);

        return R.ok(StpUtil.getTokenInfo());
    }

    @PostMapping("logout")
    public R<Void> logout() {
        StpUtil.logout();
        return R.ok();
    }
}
