# Fast Server - 基于fast-crud的高性能开箱即用的系统脚手架

![GitHub](https://img.shields.io/github/license/pengxianggui/fast-server)
![Java](https://img.shields.io/badge/java-8+-blue.svg)
![Spring Boot](https://img.shields.io/badge/spring%20boot-2.6.8-brightgreen.svg)

## 📖 项目介绍

Fast Server是一个基于Spring Boot的高性能系统脚手架，**核心集成了[fast-crud](http://fastcrud-doc.pengxg.cc/)框架**，专注于快速开发企业级后台管理系统。通过fast-crud的强大功能，可以极大地提高表格类数据维护功能的开发效率，减少重复代码编写。

> ⚠️ 注意: 当前项目仅包含后端部分，前端部分需单独集成。前端配套项目见:[fast-admin](https://github.com/pengxianggui/fast-admin)

## ✨ 核心特性

### 🔧 基础框架
- **Spring Boot 2.6.8** - 稳定可靠的Java开发框架
- **MyBatis Plus** - 强大的ORM框架，简化数据库操作
- **MyBatis Plus Join** - 基于MP实现链表跨表操作
- **Spring Security + JWT** - 完善的认证与授权机制
- **fast-crud** - 快速开发表格类数据维护功能的核心框架

### 🚀 内置功能

- ✅ **认证&鉴权** - JWT令牌认证，基于角色的权限控制
- ✅ **用户管理** - 用户信息增删改查、密码加密
- ✅ **角色管理** - 角色信息维护，角色权限绑定
- ✅ **权限管理** - 权限资源管理，权限树结构
- 🔄 **字典管理** - 系统字典维护（开发中）
- ✅ **国际化** - 支持多语言切换，默认中文/英文
- 🔄 **操作日志** - 系统操作日志记录（开发中）

### 🎯 fast-crud 核心优势

Fast-crud是本项目的核心组件，提供了以下强大功能：

- **代码生成** - 可生成代码即可快速实现CRUD功能
- **自动表格渲染** - 自动根据实体类生成表格列
- **灵活的查询条件** - 支持多种查询条件组合
- **批量操作** - 支持批量添加、修改、删除
- **数据校验** - 内置数据校验机制
- **分页排序** - 自动支持分页和排序
- **导出功能** - 支持Excel、CSV等格式导出
- **自定义扩展** - 支持自定义业务逻辑扩展

## 📦 安装与初始化

### 环境要求

- JDK 8+
- Maven 3.6+
- MySQL 5.7+

### 安装步骤

1. **克隆项目**
   ```bash
   git clone https://github.com/pengxianggui/fast-server.git
   cd fast-server
   ```

2. **配置数据库**
  - 修改`src/main/resources/application.yml`中的数据库配置
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://${DB_IP:127.0.0.1}:${DB_PORT:3306}/fast-server?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai
       username: ${DB_USER:root}
       password: ${DB_PASS:123456}
   ```

3. **初始化数据库**
  - 项目启动时会自动执行`classpath:schema.sql`创建表结构
  - 手动执行`classpath:init_data.sql`初始化基础数据

4. **启动项目**
   ```bash
   mvn spring-boot:run
   ```

## 🔧 配置说明

### 国际化配置

```yaml
spring:
  messages:
    basename: i18n/messages
    encoding: UTF-8
    fallback-to-system-locale: false
    use-code-as-default-message: true
```

### fast-crud配置

```yaml
# fast-crud 配置
fast-crud:
  host: ${HOST:http://localhost:8080}
  upload:
    mode: local
    local:
      dir: ${UPLOAD_DIR:${user.dir}/upload}
```

## 🤝 贡献指南

欢迎提交Issue和Pull Request！

### 开发流程

1. Fork 本仓库
2. 创建特性分支: `git checkout -b feature/AmazingFeature`
3. 提交更改: `git commit -m 'Add some AmazingFeature'`
4. 推送到分支: `git push origin feature/AmazingFeature`
5. 提交Pull Request

## 📄 许可证

本项目采用MIT许可证，详情请见[LICENSE](LICENSE)文件。

## 📞 联系方式

- 项目地址: [https://github.com/pengxianggui/fast-parent](https://github.com/pengxianggui/fast-parent)
- fast-crud文档: [http://fastcrud-doc.pengxg.cc/](http://fastcrud-doc.pengxg.cc/)
- 作者: pengxianggui

## 🙏 鸣谢

感谢所有为本项目做出贡献的开发者！

---

**Enjoy coding with Fast Server and fast-crud! 🎉**