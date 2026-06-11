# TLAS 员工管理系统

基于 Spring Boot 的企业员工管理系统后端，提供部门管理、员工管理、文件上传等功能。

## 技术栈

- **框架**: Spring Boot 4.0.5
- **Java版本**: JDK 17
- **ORM**: MyBatis Plus
- **数据库**: MySQL 8.x
- **分页**: PageHelper
- **对象存储**: 阿里云 OSS
- **构建工具**: Maven

## 项目结构

```
tlias-web-management/
├── src/
│   ├── main/
│   │   ├── java/com/itheima/
│   │   │   ├── controller/        # 控制器层
│   │   │   ├── service/           # 业务逻辑层
│   │   │   ├── mapper/            # 数据访问层
│   │   │   ├── pojo/              # 实体类
│   │   │   ├── config/            # 配置类
│   │   │   ├── exception/         # 异常处理
│   │   │   └── utils/             # 工具类
│   │   └── resources/
│   │       ├── application.yml    # 配置文件
│   │       ├── logback.xml        # 日志配置
│   │       └── com/itheima/mapper/ # MyBatis XML映射文件
│   └── test/                      # 测试代码
└── pom.xml                        # Maven配置
```

## 已完成功能

### ✅ 部门管理
- 查询全部部门
- 根据 ID 查询部门
- 新增部门
- 修改部门
- 删除部门

### ✅ 员工管理
- 条件分页查询员工（支持姓名、性别、入职日期筛选）
- 新增员工（包含工作经历）
- 修改员工（支持批量更新工作经历）
- 批量删除员工（级联删除工作经历）

### ✅ 员工日志
- 操作日志记录

### ✅ 文件上传
- 本地文件上传
- 阿里云 OSS 上传

### ✅ 全局异常处理
- 统一异常响应格式

### ✅ 其他
- 配置 `.gitignore` 文件
- 敏感信息使用环境变量

## 待开发功能

- [ ] 用户登录认证（JWT）
- [ ] 权限管理（RBAC）
- [ ] 部门树形结构
- [ ] 员工头像裁剪
- [ ] 数据导出（Excel）
- [ ] 操作日志查询接口
- [ ] Redis 缓存集成

## 快速开始

### 环境要求
- JDK 17+
- Maven 3.6+
- MySQL 8.x

### 数据库配置
1. 创建数据库 `tlias`
2. 执行 `src/main/resources/emp_expr.sql` 初始化表结构

### 启动项目
```bash
# 设置环境变量（可选）
export DB_USERNAME=root
export DB_PASSWORD=your_password

# 启动应用
cd tlias-web-management
mvn spring-boot:run
```

访问地址: `http://localhost:8080`

## API 接口

详见 [API接口文档](tlias-web-management/API接口文档.md)

## 开发日志

- 2026-06-10: 初始化项目，完成基础 CRUD 功能
- 2026-06-10: 配置 `.gitignore`，敏感信息改为环境变量

## 许可证

MIT License
