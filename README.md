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
- 执行JAR包,启动一个CMD或者其他命令行工具,执行"java -jar web.jar"命令运行。
<br>

- web访问路径：http://localhost:8080/noah-web
- swagger文档路径：http://localhost:8080/noah-web/swagger/index.html
- 账号密码：admin/admin

**引用js、css、img等静态资源文件，不要使用如下方式，不然加载不了，可直接方入static下引用**
-  mvc:
-    throw-exception-if-no-handler-found: true
-    static-path-pattern: /static/**
-  resources:
-         add-mappings: false

**SpringBoot打成war包，部署到Tomcat服务器**
- 链接：https://blog.csdn.net/qq_33512843/article/details/80951741
- SpringBoot默认达成jar包，使用SpringBoot构想web应用，默认使用内置的Tomcat。但考虑到项目需要集群部署或者进行优化时，
- 就需要打成war包部署到外部的Tomcat服务器中。
- 注意事项：
- 使用外部Tomcat部署访问的时候，application.properties(或者application.yml)中配置的
- server.port=
- server.servlet.context-path=
- 将失效，请使用tomcat的端口，tomcat，webapps下项目名进行访问。
- 为了防止应用上下文所导致的项目访问资源加载不到的问题，
- 建议pom.xml文件中<build></build>标签下添加<finalName></finalName>标签：



