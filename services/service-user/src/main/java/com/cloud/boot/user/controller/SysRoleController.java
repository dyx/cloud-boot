package com.cloud.boot.user.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.boot.common.core.util.R;
import com.cloud.boot.user.model.dto.SaveRoleDTO;
import com.cloud.boot.user.model.dto.RoleListQuery;
import com.cloud.boot.user.model.dto.RolePageQuery;
import com.cloud.boot.user.model.vo.RoleDetailVO;
import com.cloud.boot.user.model.vo.RoleListVO;
import com.cloud.boot.user.service.SysRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lhd
 */
@Tag(name = "角色接口")
@RequestMapping("/role")
@RestController
@RequiredArgsConstructor
public class SysRoleController {
    
    private final SysRoleService sysRoleService;
    
    @Operation(summary = "获取角色分页列表")
    @GetMapping("/page")
    public R<IPage<RoleListVO>> getRolePage(RolePageQuery query) {
        return R.ok(sysRoleService.getRolePage(query));
    }

    @Operation(summary = "获取角色列表")
    @GetMapping("/list")
    public R<List<RoleListVO>> listRoles(RoleListQuery query) {
        return R.ok(sysRoleService.listRoles(query));
    }

    @Operation(summary = "获取角色详情")
    @GetMapping("/{id}")
    public R<RoleDetailVO> getRoleById(@PathVariable("id") Long id) {
        return R.ok(sysRoleService.getRoleById(id));
    }

    @SaCheckPermission("role:save")
    @Operation(summary = "创建角色")
    @PostMapping
    public R<?> saveRole(@Valid @RequestBody SaveRoleDTO dto) {
        sysRoleService.saveRole(dto);
        return R.ok();
    }

    @SaCheckPermission("role:update")
    @Operation(summary = "修改角色")
    @PutMapping("/{id}")
    public R<?> updateRoleById(@PathVariable("id") Long id, @Valid @RequestBody SaveRoleDTO dto) {
        sysRoleService.updateRoleById(id, dto);
        return R.ok();
    }

    @SaCheckPermission("role:remove")
    @Operation(summary = "删除角色")
    @DeleteMapping("/{id}")
    public R<?> removeRoleById(@PathVariable("id") Long id) {
        sysRoleService.removeRoleById(id);
        return R.ok();
    }
}
