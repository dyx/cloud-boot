package com.cloud.boot.auth.controller;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.cloud.boot.auth.model.dto.LoginDTO;
import com.cloud.boot.common.core.constant.enums.UserStatusEnum;
import com.cloud.boot.common.core.exception.BizException;
import com.cloud.boot.common.core.util.R;
import com.cloud.boot.common.core.util.RHandler;
import com.cloud.boot.user.feign.UserFeignClient;
import com.cloud.boot.user.model.vo.UserAuthVO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
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
    @Value("${custom.api-encrypt.rsa.encrypted-private-key}")
    private String privateKey;
    @Value("${custom.api-encrypt.rsa.public-key}")
    private String publicKey;

    @Operation(summary = "登录")
    @PostMapping("login")
    public R<SaTokenInfo> login(@Valid @RequestBody LoginDTO loginDTO) {

        UserAuthVO userAuthVo = RHandler.of(userFeignClient.getUserAuthInfoByUsername(loginDTO.getUsername())).getData();
        if (userAuthVo == null) {
            throw new BizException("用户名或密码错误");
        }

        String password = decryptData(loginDTO.getPassword());
        if (!BCrypt.checkpw(password, userAuthVo.getPassword())) {
            throw new BizException("用户名或密码错误");
        }

        if (UserStatusEnum.DISABLED.getValue().equals(userAuthVo.getStatus())) {
            throw new BizException("用户已被禁用");
        }

        StpUtil.login(userAuthVo.getId());

        return R.ok(StpUtil.getTokenInfo());
    }

    @Operation(summary = "登出")
    @PostMapping("logout")
    public R<Void> logout() {
        StpUtil.logout();
        return R.ok();
    }

    @Operation(summary = "获取公钥")
    @GetMapping("/public-key")
    public R<String> getPublicKey() {
        return R.ok(publicKey);
    }

    private String decryptData(String plainText) {
        RSA rsa = new RSA(privateKey, null);
        return rsa.decryptStr(plainText, KeyType.PrivateKey);
    }
}
