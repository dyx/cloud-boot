package com.cloud.boot.auth.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.cloud.boot.common.core.util.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lhd
 */
@RestController
public class TestController {

    @SaCheckPermission("test")
    @GetMapping("test")
    public R<String> test() {
        return R.ok("test");
    }

    @SaCheckPermission("test1")
    @GetMapping("test1")
    public R<String> test1() {
        return R.ok("test1");
    }
}
