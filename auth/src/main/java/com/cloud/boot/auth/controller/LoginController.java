package com.cloud.boot.auth.controller;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.cloud.boot.auth.model.dto.LoginDTO;
import com.cloud.boot.common.core.exception.BizException;
import com.cloud.boot.common.core.util.R;
import com.cloud.boot.common.core.util.RHandler;
import com.cloud.boot.product.feign.InventoryFeignClient;
import com.cloud.boot.user.feign.UserFeignClient;
import com.cloud.boot.user.model.vo.UserInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lhd
 */
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserFeignClient userFeignClient;
    private final InventoryFeignClient inventoryFeignClient;
    private final StringRedisTemplate stringRedisTemplate;

    @Operation(summary = "登录")
    @PostMapping("login")
    public R<SaTokenInfo> login(@Valid @RequestBody LoginDTO loginDTO) {

        R<UserInfoVo> remoteData = userFeignClient.getUserInfoByUsername(loginDTO.getUsername());
        UserInfoVo userInfoVo = RHandler.of(remoteData).getData().orElse(null);
        if (userInfoVo == null || userInfoVo.getUserVo() == null) {
            throw new BizException("用户名或密码错误");
        }

        if (!BCrypt.checkpw(loginDTO.getPassword(), userInfoVo.getUserVo().getPassword())) {
            throw new BizException("用户名或密码错误");
        }

        StpUtil.login(userInfoVo.getUserVo().getId());

        return R.ok(StpUtil.getTokenInfo());
    }

    @Operation(summary = "登出")
    @PostMapping("logout")
    public R<Void> logout() {
        StpUtil.logout();
        return R.ok();
    }
}
