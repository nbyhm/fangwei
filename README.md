# noah
**项目说明**
- 采用SpringBoot、MyBatis、Shiro框架，开发的一套权限系统
<br>

**项目结构** 
```
noah
├─web      后台管理模块
│    ├─db  数据库SQL脚本
│    │ 
│    ├─web  模块
│    │    ├─menu 菜单
│    │    ├─role 角色
│    │    └─admin 系统管理(核心)
│    │ 
│    └─resources 
│        ├─statics  静态资源
│        ├─template 系统页面
│        │    ├─modules      模块页面
│        │    ├─index.html   起始页面（默认主题）
│        │    └─login.html  登录页面
│        └─application.yml   全局配置文件
│       
│ 
├─api        API服务模块
│ 
├─service    service模块
│
├─dal
│    ├─db   数据库SQL脚本
│    │ 
│    └─resources 
│        └─mapper   MyBatis文件
│     
│
├─util    工具模块
│
```

<br>

 **技术选型：** 
- 核心框架：Spring Boot 2.5
- 安全框架：Apache Shiro 1.4
- 持久层框架：MyBatis 3.3
- 数据库连接池：Druid 1.1
- 日志管理：SLF4J 1.16

<br>

 **软件需求** 
- JDK1.8
- MySQL5.6+
- Maven3.5.0+

<br>

 **本地部署**
- 通过git下载源码
- 创建数据库noah，数据库编码为UTF-8
- 执行db/mysql.sql文件，初始化数据【按需导入表结构及数据】
- 修改application-dev.yml文件，更新MySQL账号和密码
- 在noah目录下，执行mvn clean install
<br>

- web访问路径：http://localhost:8080/noah-web
- swagger文档路径：http://localhost:8080/noah-web/swagger/index.html
- 账号密码：admin/admin

