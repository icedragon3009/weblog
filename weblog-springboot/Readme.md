# Weblog 后端工程
## 准备

### 环境
- JDK 1.8 版本（此版本是目前企业中使用最广泛的）;
- MySQL 5.7 版本 (或者 8.x 版本都可以，本项目使用的是 5.7 版本)；
- Maven 3.8 版本 （项目构建工具）;

### 工程搭建
使用springboot 多模块搭建

#### 父项目：weblog-springboot
定义项目的统一配置，如项目版本号
```xml
    <!-- 版本号统一管理 -->
    <properties>
        <!-- 项目版本号 -->
        <revision>0.0.1-SNAPSHOT</revision>
        <java.version>1.8</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>

        <!-- Maven 相关 -->
        <maven.compiler.source>${java.version}</maven.compiler.source>
        <maven.compiler.target>${java.version}</maven.compiler.target>
        <!-- 依赖包版本 -->
        <lombok.version>1.18.28</lombok.version>
        <guava.version>31.1-jre</guava.version>
        <commons-lang3.version>3.12.0</commons-lang3.version>
        <jackson.version>2.15.2</jackson.version>
        <knife4j.version>4.3.0</knife4j.version>

    </properties>
```
子模块管理
```xml
    <!-- 子模块管理 -->
    <modules>
        <!-- 入口模块 -->
        <module>weblog-web</module>
        <!-- 管理后台 -->
        <module>weblog-admin</module>
        <!-- 通用模块 -->
        <module>weblog-common</module>
    </modules>
```

#### weblog-web
前端入口，提供RESTAPI
#### weblog-admin
管理后台，提供对应RESTAPI
#### weblog-common
公共组件管理

### 多环境配置


## 代码
### 通用组件
#### 日志管理
- 自定义注解，实现API请求日志切面
- 通过MDC实现日志追踪

#### 参数校验
- 使用JSR 380 参数校验注解

引用依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

- 自定义响应工具类
#### 异常捕获

#### Keife4j，接口文档和调试