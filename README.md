# Tlias 教务管理系统

![Java](https://img.shields.io/badge/Java-17-007396?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?logo=springboot&logoColor=white)
![MyBatis](https://img.shields.io/badge/ORM-MyBatis-165D9B)
![MySQL](https://img.shields.io/badge/Database-MySQL-4479A1?logo=mysql&logoColor=white)

基于 Spring Boot + MyBatis + MySQL 的教务管理系统课程项目，包含部门、员工、班级、学生、日志和报表等模块。

## 系统架构

![Tlias 教务管理系统架构](docs/architecture.png)

请求从 Controller 进入，经 Service 业务层调用 MyBatis Mapper，最终访问 MySQL；JWT 过滤器负责登录校验，AOP 负责操作日志记录。

## 功能模块

| 模块 | 说明 |
| --- | --- |
| 部门管理 | 部门增删改查 |
| 员工管理 | 员工信息、分页查询、批量操作 |
| 班级管理 | 班级信息维护 |
| 学生管理 | 学生信息维护 |
| 日志管理 | 操作日志记录与查询 |
| 报表统计 | 员工、班级等统计报表 |
| 文件上传 | 图片/文件上传接口 |
| 登录认证 | JWT 登录与 Token 校验 |

## 技术栈

- Java 17、Spring Boot、Spring MVC
- MyBatis、PageHelper
- MySQL
- JWT、AOP、Filter/Interceptor
- Maven 多模块

## 环境要求

- JDK 17+
- Maven 3.8+
- MySQL 8.0+

## 数据库配置

数据库名：tlias

在 Tlias-web-management/src/main/resources/application.yml 中配置自己的数据库地址，或通过环境变量设置：

~~~bash
export MYSQL_USERNAME=root
export MYSQL_PASSWORD=your-secret-here
~~~

不要将真实数据库密码提交到仓库。

## 启动方式

1. 在 MySQL 中创建 tlias 数据库并导入项目提供的 SQL 脚本（如有）。
2. 修改 application.yml 中的数据库连接信息。
3. 在项目根目录执行：

~~~bash
mvn clean package
java -jar Tlias-web-management/target/Tlias-web-management-0.0.1-SNAPSHOT.jar
~~~

或在 IntelliJ IDEA 中直接运行 TliasWebManagementApplication.java。

## 目录结构

~~~text
MyTliasManagement/
├── tlias-parent/
├── tlias-pojo/
├── tlias-utils/
└── Tlias-web-management/
    ├── src/main/java/org/example/tliaswebmanagement/
    │   ├── controller/
    │   ├── service/
    │   ├── mapper/
    │   ├── exception/
    │   ├── filter/
    │   └── interceptor/
    └── src/main/resources/
        ├── application.yml
        └── logback.xml
~~~

## 说明

本项目是 Java Web 课程练习的整理版本，保留原有 Spring Boot 多模块结构和业务代码。构建产物 target/ 已从仓库中移除，建议本地构建后自行生成。
