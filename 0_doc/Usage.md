## @Inner 内部服务注解
仅限微服务间内部调用，使用此注解，外部访问会提示“访问被拒绝”。

微服务间使用 Feign 调用时，会默认在请求头增加 Inner: true。通过网关而来的外部请求，在请求头上不会携带 Inner 属性（会判断请求头中是否 Inner 属性，有则会移除，防止伪造内部调用）。 

使用 @Inner 注解时，判断请求头中 Inner 属性，true 则放行，false 则拒绝。
### 使用
引入依赖
```xml
<dependencies>
    <dependency>
        <groupId>com.cloud.boot</groupId>
        <artifactId>common-feign</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```
在 Controller 方法上增加 @Inner 注解
```java
@Inner
@GetMapping("/username/{username}")
public R<UserAuthVO> getUserAuthInfoByUsername(@PathVariable("username") String username) {
    return R.ok(sysUserService.getUserAuthInfoByUsername(username));
}
```
## @Sensitive 脱敏注解
引入依赖
```xml
<dependencies>
    <dependency>
        <groupId>com.cloud.boot</groupId>
        <artifactId>common-mybatis</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```
在 DO 上的字段增加`@Sensitive`注解并设置策略
```java
@TableName("sys_user")
@Data
public class SysUserDO {

    /**
     * 用户邮箱
     */
    @Sensitive(strategy = SensitiveStrategy.EMAIL)
    private String email;

    /**
     * 手机号
     */
    @Sensitive(strategy = SensitiveStrategy.PHONE)
    private String phone;
}
```
### 注意
1.目前只支持List、单个实体的查询结果
2.暂不支持层级嵌套实体

## @Translate 字段翻译注解
很多时候我们需要关联另一张表查询数据，比如关联字典查询状态，关联用户查询用户名称等，可以使用该注解
### 使用
1. 引入依赖
```xml
<dependencies>
    <dependency>
        <groupId>com.cloud.boot</groupId>
        <artifactId>common-translation-annotation</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
    <dependency>
        <groupId>com.cloud.boot</groupId>
        <artifactId>common-translation-core</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```
2. 在需要翻译的字段上增加`@Translate`注解
```java
@Translate(type = TranslatorTypeEnum.DICT, dictCode = "user_status")
private String status;
private String statusName;

@Translate(type = TranslatorTypeEnum.USER, target = {
        @TranslateMapping(mappingKey = TranslateMappingKeyConstant.USER_NICKNAME, target = "createByName"),
        @TranslateMapping(mappingKey = TranslateMappingKeyConstant.USER_USERNAME, target = "createByUsername")
})
private Long createBy;
private String createByName;
private String createByUsername;
```
3. 在返回该字段的方法上增加`@EnableTranslation`注解
```java
@EnableTranslation
@Override
public IPage<UserListVO> getUserPage(UserPageQuery query) {
    // ...
}
```
### 高级
如果要增加翻译类型，可实现接口`Translator`，并交由 Spring 容器管理
```java
@Component
public class CustomTranslator implements Translator<String> {

    @Override
    public Map<String, Map<String, Object>> translate(Set<String> sourceValueSet) {
        return Map.of();
    }

    @Override
    public String getType() {
        return TranslatorTypeConstant.CUSTOM;
    }
}
```
### 注意
1. 翻译工具目前只支持 IPage（MybatisPlus）、List、单个实体的返回值
2. 字典类型翻译，翻译值字段为`源字段+Name`，如源字段为`status`，则翻译值字段为`statusName`
3. 字典类型只支持 1 对 1 翻译，其他类型支持 1 对多翻译，通过指定多个`@TranslateMapping`实现

## 操作日志

## 数据权限