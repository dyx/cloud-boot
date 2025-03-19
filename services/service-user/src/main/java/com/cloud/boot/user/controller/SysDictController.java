package com.cloud.boot.user.controller;

import com.cloud.boot.common.core.util.R;
import com.cloud.boot.common.feign.annotation.Inner;
import com.cloud.boot.user.service.SysDictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

/**
 * @author lhd
 */
@Tag(name = "字典接口")
@RequestMapping("/dict")
@RestController
@RequiredArgsConstructor
public class SysDictController {

    private final SysDictService sysDictService;

    @Inner
    @Operation(summary = "批量翻译字典信息")
    @PostMapping("/translate/batch")
    public R<Map<String, Map<String, Object>>> batchTranslateDict(@RequestBody Set<String> sourceValueSet) {
        return R.ok(sysDictService.batchTranslateDict(sourceValueSet));
    }
}
