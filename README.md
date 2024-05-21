# 工程介绍
> 本工程是一套基础的Spring Cloud Alibaba 微服务架构，整合了权限框架Oauth2.0，
> 能满足大部分基础的日常开发需求，您也可以在该项目中定制自己需要的功能。

# 工程架构
> 项目分为五个模块，分别是：
1. gateway：网关服务，负责统一鉴权，路由转发。
2. auth：授权服务，负责用户登录时的验证，Token颁发。
3. system：系统服务，负责系统管理，用户管理，角色管理，菜单管理，权限管理等基础信息的维护；
目前只有基础的用户-角色关系的简单维护接口，后续会继续完善。
4. business-one：业务服务，负责业务逻辑的实现，只是一个测试feign调用的例子模块，您可以自行扩展更多业务模块。
5. common：公共模块，包含基础的配置，工具类，常量定义等；
注意，该模块引入了MybatisPlus相关依赖，便于feign调用时，其他业务服务共同使用该模块的实体对象，减少重复代码。

# 技术选型
1. JDK开发版本：corretto-17 Amazon Corretto version 17.0.11 - aarch64
2. 微服务基础框架：Spring Cloud Hoxton.SR12 <-> Spring Cloud Alibaba 2.2.9.RELEASE
3. Nacos：作为注册中心，配置中心，服务发现组件。
4. OAuth2.0 + JWT：作为权限框架，负责用户登录时的验证，Token颁发。
5. Redis：作为缓存中间件。
6. MySQL 8.x：作为数据库。
7. MybatisPlus：作为ORM框架。

# 快速开始
## 中间件准备工作
* 安装MySQL8.x，[可选] 修改配置文件，并启动。
* 安装Redis5.x，[可选] 修改配置文件（主要修改密码，端口），并启动。
* 安装Nacos2.x服务端，修改配置文件（主要修改数据库配置）并启动。
## 脚本执行
* 在MySQL中创建数据库[maxs]，选中数据库[maxs]，执行init/db.sql脚本，创建相关表。
* 访问http://localhost:8848/nacos，创建命名空间，然后在配置列表导入配置文件，配置文件在init目录下。
* 修改nacos中的相关配置信息，如MySQL、Redis、OAuth等配置。
* 修改各个服务模块中的logback-spring.xml，其中的配置信息（如日志路径、日志级别等）。
* 启动各个服务模块进行测试。
## 当前默认系统、用户信息
### 系统信息

| client_id    | client_secret |
|--------------|---------------|
| maxs-manager | system123456  |
| maxs-web     | system123456  |

### 用户信息
| username | password |
|----------|----------|
| zhangsan | 123456   |
| lisi     | 123456   |

## 添加新的client步骤：
数据库表[oauth_client_details]中新加一行，其中的client_secret可以通过auth模块中的src/test/java/GeneratePwd生成。

## 添加新的用户步骤：
1. 添加用户信息到数据库表[sys_user]中。
2. 为用户分配角色，到数据库表[sys_user_role]中。
3. 检查角色对应权限是否正确[sys_role_permission]。