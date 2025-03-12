package com.cloud.boot.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.boot.common.core.util.R;
import com.cloud.boot.common.core.util.RHandler;
import com.cloud.boot.common.feign.annotation.Inner;
import com.cloud.boot.order.feign.OrderFeignClient;
import com.cloud.boot.order.model.dto.SaveOrderDTO;
import com.cloud.boot.order.model.dto.SaveOrderItemDTO;
import com.cloud.boot.product.feign.InventoryFeignClient;
import com.cloud.boot.product.model.dto.DeductStockDTO;
import com.cloud.boot.user.model.dto.SaveUserDTO;
import com.cloud.boot.user.model.dto.UpdateUserDTO;
import com.cloud.boot.user.model.dto.UserListQuery;
import com.cloud.boot.user.model.dto.UserPageQuery;
import com.cloud.boot.user.model.vo.UserAuthVO;
import com.cloud.boot.user.model.vo.UserDetailVO;
import com.cloud.boot.user.model.vo.UserInfoVO;
import com.cloud.boot.user.model.vo.UserListVO;
import com.cloud.boot.user.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lhd
 */
@Tag(name = "用户接口")
@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class SysUserController {

    private final InventoryFeignClient inventoryFeignClient;
    private final OrderFeignClient orderFeignClient;
    private final SysUserService sysUserService;

    @Inner
    @Operation(summary = "根据用户名获取用户信息")
    @GetMapping("/username/{username}")
    public R<UserAuthVO> getUserAuthInfoByUsername(@PathVariable("username") String username) {
        return R.ok(sysUserService.getUserAuthInfoByUsername(username));
    }

    @Operation(summary = "获取当前用户信息")
    @GetMapping("/current")
    public R<UserInfoVO> getCurrentUserInfo() {
        return R.ok(sysUserService.getCurrentUserInfo());
    }

    @Operation(summary = "获取用户分页列表")
    @GetMapping("/page")
    public R<IPage<UserListVO>> getUserPage(UserPageQuery query) {
        return R.ok(sysUserService.getUserPage(query));
    }

    @Operation(summary = "获取用户列表")
    @GetMapping("/list")
    public R<List<UserListVO>> listUsers(UserListQuery query) {
        return R.ok(sysUserService.listUsers(query));
    }

    @Operation(summary = "获取用户详情")
    @GetMapping("/{id}")
    public R<UserDetailVO> getUserDetailById(@PathVariable("id") Long id) {
        return R.ok(sysUserService.getUserDetailById(id));
    }

    @Operation(summary = "创建用户")
    @PostMapping
    public R<?> saveUser(@Valid @RequestBody SaveUserDTO dto) {
        sysUserService.saveUser(dto);
        return R.ok();
    }

    @Operation(summary = "修改用户")
    @PutMapping("/{id}")
    public R<?> updateUserById(@PathVariable("id") Long id, @Valid @RequestBody UpdateUserDTO dto) {
        sysUserService.updateUserById(id, dto);
        return R.ok();
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("/{id}")
    public R<?> removeUserById(@PathVariable("id") Long id) {
        sysUserService.removeUserById(id);
        return R.ok();
    }

    @Operation(summary = "采购商品")
    @GlobalTransactional
    @PostMapping("/purchase")
    public R<?> purchase(@Valid @RequestBody SaveOrderDTO dto) {

        List<DeductStockDTO> deductStockDTOList = new ArrayList<>();
        for (SaveOrderItemDTO saveOrderItemDTO : dto.getOrderItemList()) {
            DeductStockDTO deductStockDTO = new DeductStockDTO();
            deductStockDTO.setProductId(saveOrderItemDTO.getProductId());
            deductStockDTO.setStock(saveOrderItemDTO.getQuantity());
            deductStockDTOList.add(deductStockDTO);
        }

        RHandler.of(orderFeignClient.saveOrder(dto)).failThenThrow();

        RHandler.of(inventoryFeignClient.deductStockBatch(deductStockDTOList)).failThenThrow();

        return R.ok();
    }
}
