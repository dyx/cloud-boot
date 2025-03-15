## Inner注解
仅限微服务间内部调用，使用此注解，外部访问会提示“访问被拒绝”。

微服务间使用 Feign 调用时，会默认在请求头增加 Inner: true。通过网关而来的外部请求，在请求头上不会携带 Inner 属性（会判断请求头中是否 Inner 属性，有则会移除，防止伪造内部调用）。 

使用 @Inner 注解时，判断请求头中 Inner 属性，true 则放行，false 则拒绝。
### 使用
在 Controller 方法上增加 @Inner 注解
```java
@Inner
@GetMapping("/username/{username}")
public R<UserAuthVO> getUserAuthInfoByUsername(@PathVariable("username") String username) {
    return R.ok(sysUserService.getUserAuthInfoByUsername(username));
}
```