# 微服务后端脚手架
## 功能

## 依赖
### 环境
- Java 17
- MySQL 8.0
- Nacos 2.5.0
- Sentinel 1.8.8
- Seata 2.2.0
### Jar包
- spring-boot 3.2.12
- spring-cloud 2023.0.4
- spring-cloud-alibaba 2023.0.3.2
- mybatis-plus 3.5.10.1
- mapstruct 1.6.3
- sa-token 1.4.0
- knife4j 4.5.0
## 项目结构
```
├── apis 公共api
│   ├── api-order
│   ├── api-product
│   └── api-user
├── auth 授权服务，颁发 token
├── common
│   ├── common-config 公共配置，全局异常处理、请求日志、接口文档配置、响应序列化配置
│   ├── common-core 公共工具类核心包，全局常量、全局异常、统一响应结果
│   ├── common-feign feign 扩展封装
│   ├── common-mybatis mybatis 扩展封装，插件配置、自动填充字段
│   └── common-resource-server 公共鉴权
├── gateway 网关服务
└── services 业务服务
    ├── service-order
    ├── service-product
    └── service-user
```
## 运行
### 一、环境准备
建议本地新建两个文件夹，一个放置Docker相关，一个放置jar包相关
#### Java17
本地需要安装Java环境，用于运行Sentinel和Seata
#### MySQL8.0
```yaml
services:
  mysql:
    image: mysql:8.0
    container_name: mysql8
    environment:
      MYSQL_ROOT_PASSWORD: 123456
      MYSQL_DATABASE: test
    command:
      --character-set-server=utf8mb4
      --collation-server=utf8mb4_general_ci
      --default-time_zone='+8:00'
    ports:
      - 3306:3306
    volumes:
      # 改为自己的映射目录
      - ~/docker/docker-data/mysql8/data:/var/lib/mysql
      - ~/docker/docker-data/mysql8/conf:/etc/mysql/conf.d
    restart: on-failure
```
1. 使用上面的Yaml文件，部署 MySQL `docker compose up -d`
2. 创建三个数据库：`order`、`product`、`user`
3. 将`0_db`目录下的 SQL 在对应的数据库中执行
#### Redis7
1. 创建文件夹`~/develop/docker/docker-data/redis/conf`，下载官方配置文件，移动到该文件夹中
   - 官网配置文件地址 https://download.redis.io/redis-stable/redis.conf
2. 使用下面的Yaml文件，部署 Redis `docker compose up -d`
```yaml
version: '3'
services:
  mysql:
    image: redis:7
    container_name: redis7
    ports:
      - 6379:6379
    volumes:
      - ~/develop/docker/docker-data/redis/conf:/usr/local/etc/redis
      - ~/develop/docker/docker-data/redis/data:/data
    environment:
      - ALLOW_EMPTY_PASSWORD=false
      # 如修改为其他，需要修改本项目的 application.yml
      - REDIS_PASSWORD=123456
    restart: always
```
#### Nacos
1. 下载 nacos-docker 项目到本地，地址 https://github.com/nacos-group/nacos-docker.git
2. 上一步，部署的MySQL端口为3306，与Nacos的MySQL端口会有冲突，所以需要修改`example/standalone-mysql-8.yaml`中的端口，如修改为`- "3310:3306"`
3. 启用登录功能，修改配置`env/nacos-standalone-mysql.env`下的属性`NACOS_AUTH_ENABLE=true`（如没有则增加）
4. cd 进入 nacos-docker 项目目录，执行命令`docker-compose -f example/standalone-mysql-8.yaml up -d`
5. 访问 http://localhost:8848/nacos/ 初始化管理员用户名密码：nacos/nacos（如修改为其他，需要修改本项目的 application.yml 以及 Seata 的配置文件）
#### Sentinel
1. 下载jar包，地址 https://github.com/alibaba/Sentinel/releases
2. 命令窗口运行：`java -jar sentinet-dashboard-x.x.x.jar`（版本更换为自己下载的版本）
3. 访问 http://localhost:8080 默认用户名密码：`sentinel/sentinel`
#### Seata
1. 下载Binary包并解压，地址 https://seata.apache.org/download/seata-server
2. 进入seata-server/conf目录，修改application.yml文件，使用Nacos作为注册中心
```yaml
registry:
    type: nacos
    nacos:
      application: seata-server
      server-addr: 127.0.0.1:8848
      group: SEATA_GROUP
      namespace:
      cluster: default
      username: nacos
      password: nacos
      ##if use MSE Nacos with auth, mutex with username/password attribute
      #access-key: ""
      #secret-key: ""
```
3. 进入seata-server/bin目录，执行命令：`sh seata-server.sh`
4. 访问 http://localhost:7091 默认用户名密码：`seata/seata`
### 二、启动项目
1. 克隆代码
```
git clone https://github.com/zhoutaoo/SpringCloud.git
```
2. 安装依赖
```
cd cloud-boot
mvn clean install
```
3. 运行项目
- AuthApplication
- ProductApplication
- OrderApplication
- UserApplication
- GatewayApplication
### 三、访问项目
接口文档地址 `http://localhost:9999/doc.html`
## 部署

### 四、其他
#### 命名规范
- Service / Mapper
  - get[User]，获取单个对象
  - list[Users]，获取列表对象，复数结尾
  - count[User]，获取统计值
  - save / insert[User]，新增
  - remove / delete[User]，删除
  - update[User]，修改
  - Controller 层的方法命名与 Service 一致
- model
  - 数据对象：[xxx]DO，xxx为表名，如 SysUserDO
  - 数据传输对象：[xxx]DTO，xxx为领域相关操作名，如 SaveUserDTO
  - 展示对象：[xxx]VO，xxx为页面名称，如 UserListVO
- permission
  - 领域对象:操作，如 user:save、user:remove、user:update
#### 使用规范
- 依赖注入，推荐使用构造器注入，搭配 @RequiredArgsConstructor 注解使用
  - ，搭配 @RequiredArgsConstructor 注解，该注解生成的构造参数为：仅 final 字段和未初始化的 @NonNull 字段
```java
@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
}
```
#### 数据库
> 建表模板
```sql
CREATE TABLE `table_name` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `create_by` bigint  NOT NULL DEFAULT 0 COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint  NOT NULL DEFAULT 0 COMMENT '修改人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_time` bigint default 0 not null comment '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB  COMMENT='表名';
```