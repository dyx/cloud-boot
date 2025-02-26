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
- knife4j 4.5.0
## 项目结构
```
├── apis 公共api
│   ├── api-order
│   ├── api-product
│   └── api-user
├── auth 授权服务
├── common
│   ├── common-core 公共工具类核心包，全局常量、全局异常、统一响应结果
│   ├── common-feign feign 扩展封装，全局异常处理、请求日志、接口文档配置、响应序列化配置
│   └── common-mybatis mybatis 扩展封装，插件配置、自动填充字段
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
本地需要安装Java环境，用于运行Sentinel
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
3. 将`db`目录下的 SQL 在对应的数据库中执行
#### Nacos
1. 下载 nacos-docker 项目到本地，地址 https://github.com/nacos-group/nacos-docker.git
2. 上一步，部署的MySQL端口为3306，与Nacos的MySQL端口会有冲突，所以需要修改`example/standalone-mysql-8.yaml`中的端口，如修改为`- "3310:3306"`
3. 启用登录功能，修改配置`env/nacos-standalone-mysql.env`下的属性`NACOS_AUTH_ENABLE=true`（如没有则增加）
4. cd 进入 nacos-docker 项目目录，执行命令`docker-compose -f example/standalone-mysql-8.yaml up -d`
5. 访问 http://localhost:8848/nacos/ 初始化管理员用户名密码：nacos/nacos（如修改为其他，需要修改本项目的 application.yml 以及 Seata 的配置文件）
#### Seata
1. 复制配置文件
```basah
docker run -d -p 8091:8091 -p 7091:7091  --name seata-server apache/seata-server:2.2.0
mkdir -p ~/develop/docker/docker-data/seata-server
docker cp seata-server:/seata-server/resources ~/develop/docker/docker-data/seata-server
docker stop seata-server
docker rm seata-server
```
2. 修改配置 resources/application.yml，将Seata注册到Nacos
```yaml
seata:
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
3. 使用下面 Yaml 文件，部署 seata-server `docker compose up -d`
```yaml
version: "3"
services:
  seata-server:
    image: apache/seata-server:2.2.0
    hostname: seata-server
    container_name: seata-server
    network_mode: host
    environment:
      - SEATA_IP=[改成自己的宿主机IP]
      - SEATA_PORT=8091
    volumes:
      - ~/develop/docker/docker-data/seata-server/resources:/seata-server/resources
      - ~/develop/docker/docker-data/seata-server/sessionStore:/seata-server/sessionStore
    expose:
      - 8091
      - 7091
    restart: always
```
访问 http://localhost:7091 默认用户名密码：`seata/seata`
#### Sentinel
1. 下载jar包，地址 https://github.com/alibaba/Sentinel/releases
2. 命令窗口运行：`java -jar sentinel-dashboard-x.x.x.jar`（版本更换为自己下载的版本）
3. 访问 http://localhost:8080 默认用户名密码：`sentinel/sentinel`
- 这里没有使用Docker部署，因为Docker远程仓库没有官方的sentinel-dashboard镜像，如果想要使用Docker部署，建议下载jar包配合本地Dockerfile进行部署
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
- ProductApplication
- OrderApplication
- UserApplication
- GatewayApplication
### 三、访问项目
接口文档地址 `http://localhost:9999/doc.html`
## 部署