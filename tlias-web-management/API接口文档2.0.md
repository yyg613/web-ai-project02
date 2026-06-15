# Tlias 员工管理系统 - API 接口文档

> **基础路径**：`http://localhost:8080`
> **代理说明**：开发环境前端通过 Vite 将 `/api` 前缀请求代理到 `http://localhost:8080`，并自动去除 `/api` 前缀

---

## 一、通用约定

### 1.1 统一响应格式

```json
{
  "code": 1,
  "msg": "success",
  "data": {}
}
```

> `code`：业务状态码，1-成功，0-失败
> `msg`：提示信息，失败时携带具体错误原因
> `data`：响应数据，成功时为业务数据，失败时为 `null`

| 字段 | 类型   | 说明                                   |
|------|--------|----------------------------------------|
| code | int    | 1 表示成功，0 表示失败               |
| msg  | string | 失败时携带具体错误原因                 |
| data | any    | 成功时为业务数据，失败时为 `null`     |

### 1.2 分页响应格式

```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "total": 100,
    "rows":  []
  }
}
```

### 1.3 时间格式

| 类型           | 格式示例                 | 说明     |
|----------------|---------------------------|----------|
| LocalDateTime  | `2024-01-01T10:00:00`    | 日期时间 |
| LocalDate      | `2024-01-01`              | 纯日期   |

---

## 二、部门管理 `/depts`

### 2.1 查询全部部门

```
GET /depts
```

无请求参数，返回部门列表。

**响应示例**

```json
{
  "code": 1,
  "msg": "success",
  "data": [
    {
      "id": 1,
      "name": "学工部",
      "createTime": "2024-01-01T10:00:00",
      "updateTime": "2024-06-01T12:00:00"
    },
    {
      "id": 2,
      "name": "教研部",
      "createTime": "2024-01-01T10:00:00",
      "updateTime": "2024-06-01T12:00:00"
    }
  ]
}
```

| 返回字段   | 类型     | 说明     |
|------------|----------|----------|
| id         | int      | 部门ID   |
| name       | string   | 部门名称 |
| createTime | datetime | 创建时间 |
| updateTime | datetime | 修改时间 |

---

### 2.2 根据 ID 查询部门

```
GET /depts/{id}
```

**路径参数**

| 参数 | 类型 | 必填 | 说明   |
|------|------|------|--------|
| id   | int  | 是   | 部门ID |

**响应示例**

```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "id": 1,
    "name": "学工部",
    "createTime": "2024-01-01T10:00:00",
    "updateTime": "2024-06-01T12:00:00"
  }
}
```

---

### 2.3 新增部门

```
POST /depts
Content-Type: application/json
```

**请求体**

| 参数 | 类型   | 必填 | 说明     |
|------|--------|------|----------|
| name | string | 是   | 部门名称 |

**请求示例**

```json
{
  "name": "市场部"
}
```

**响应示例**

```json
{
  "code": 1,
  "msg": "success",
  "data": null
}
```

---

### 2.4 修改部门

```
PUT /depts/{id}
Content-Type: application/json
```

**路径参数**

| 参数 | 类型 | 必填 | 说明   |
|------|------|------|--------|
| id   | int  | 是   | 部门ID |

**请求体**

| 参数 | 类型   | 必填 | 说明     |
|------|--------|------|----------|
| name | string | 是   | 部门名称 |

**请求示例**

```json
{
  "name": "市场部（改名）"
}
```

**响应示例**

```json
{
  "code": 1,
  "msg": "success",
  "data": null
}
```

---

### 2.5 删除部门

```
DELETE /depts/{id}
```

**路径参数**

| 参数 | 类型 | 必填 | 说明   |
|------|------|------|--------|
| id   | int  | 是   | 部门ID |

**响应示例**

```json
{
  "code": 1,
  "msg": "success",
  "data": null
}
```

---

## 三、员工管理 `/emps`

### 3.1 条件分页查询员工

```
GET /emps
```

**Query 参数**

| 参数     | 类型   | 必填 | 默认值 | 说明                        |
|----------|--------|------|--------|-----------------------------|
| page     | int    | 否   | 1      | 当前页码                    |
| pageSize | int    | 否   | 10     | 每页记录数                  |
| name     | string | 否   | -      | 员工姓名（模糊匹配）        |
| gender   | int    | 否   | -      | 性别：1-男，2-女            |
| begin    | string | 否   | -      | 入职日期范围-开始，`yyyy-MM-dd` |
| end      | string | 否   | -      | 入职日期范围-结束，`yyyy-MM-dd` |

**请求示例**

```
GET /emps?page=1&pageSize=10&name=张&gender=1&begin=2024-01-01&end=2024-12-31
```

**响应示例**

```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "total": 50,
    "rows": [
      {
        "id": 1,
        "username": "zhangsan",
        "password": "123456",
        "name": "张三",
        "gender": 1,
        "phone": "13800138000",
        "job": 1,
        "salary": 8000,
        "image": "https://example.com/avatar.jpg",
        "entryDate": "2024-03-15",
        "deptId": 1,
        "createTime": "2024-03-15T10:00:00",
        "updateTime": "2024-06-01T12:00:00",
        "deptName": "学工部",
        "empList": [
          {
            "id": 1,
            "empId": 1,
            "company": "百度",
            "job": "Java开发",
            "begin": "2020-07-01",
            "end": "2023-06-30"
          }
        ]
      }
    ]
  }
}
```

**rows 字段说明**

| 字段       | 类型   | 说明                                          |
|------------|--------|-----------------------------------------------|
| id         | int    | 员工ID                                        |
| username   | string | 登录用户名                                    |
| password   | string | 密码（脱敏返回）                              |
| name       | string | 姓名                                          |
| gender     | int    | 性别：1-男，2-女                              |
| phone      | string | 手机号                                        |
| job        | int    | 职位编码（见数据字典）                        |
| salary     | int    | 薪资                                          |
| image      | string | 头像 URL                                      |
| entryDate  | string | 入职日期，`yyyy-MM-dd`                        |
| deptId     | int    | 所属部门 ID                                   |
| deptName   | string | 所属部门名称（关联查询填充）                  |
| createTime | string | 创建时间                                      |
| updateTime | string | 最后修改时间                                  |
| empList    | array  | 工作经历列表                                  |

**empList 子字段**

| 字段    | 类型   | 说明                        |
|---------|--------|-----------------------------|
| id      | int    | 工作经历ID                  |
| empId   | int    | 归属员工ID                  |
| company | string | 公司名称                    |
| job     | string | 职位                        |
| begin   | string | 开始日期，`yyyy-MM-dd`      |
| end     | string | 结束日期，`yyyy-MM-dd`      |

---

### 3.2 新增员工

```
POST /emps
Content-Type: application/json
```

**请求体**

| 参数      | 类型   | 必填 | 说明                                          |
|-----------|--------|------|-----------------------------------------------|
| username  | string | 是   | 登录用户名                                    |
| password  | string | 是   | 登录密码                                      |
| name      | string | 是   | 姓名                                          |
| gender    | int    | 是   | 性别：1-男，2-女                              |
| phone     | string | 是   | 手机号                                        |
| job       | int    | 否   | 职位编码，见数据字典                          |
| salary    | int    | 否   | 薪资                                          |
| image     | string | 否   | 头像 URL，一般由上传接口获得                  |
| entryDate | string | 否   | 入职日期，`yyyy-MM-dd`                        |
| deptId    | int    | 是   | 所属部门 ID                                   |
| empList   | array  | 否   | 工作经历列表                                  |

**empList 子字段**

| 字段    | 类型   | 必填 | 说明                        |
|---------|--------|------|-----------------------------|
| company | string | 否   | 公司名称                    |
| job     | string | 否   | 职位                        |
| begin   | string | 否   | 开始日期，`yyyy-MM-dd`      |
| end     | string | 否   | 结束日期，`yyyy-MM-dd`      |

**请求示例**

```json
{
  "username": "lisi",
  "password": "123456",
  "name": "李四",
  "gender": 2,
  "phone": "13900139000",
  "job": 2,
  "salary": 10000,
  "image": "https://example.com/avatar2.jpg",
  "entryDate": "2024-06-01",
  "deptId": 2,
  "empList": [
    {
      "company": "腾讯",
      "job": "Java开发",
      "begin": "2021-07-01",
      "end": "2024-05-31"
    },
    {
      "company": "阿里巴巴",
      "job": "实习开发",
      "begin": "2020-07-01",
      "end": "2021-06-30"
    }
  ]
}
```

**响应示例**

```json
{
  "code": 1,
  "msg": "success",
  "data": null
}
```

---

### 3.3 修改员工

```
PUT /emps/{id}
Content-Type: application/json
```

**路径参数**

| 参数 | 类型 | 必填 | 说明   |
|------|------|------|--------|
| id   | int  | 是   | 员工ID |

**请求体**

| 参数      | 类型   | 必填 | 说明                                          |
|-----------|--------|------|-----------------------------------------------|
| name      | string | 是   | 姓名                                          |
| gender    | int    | 是   | 性别：1-男，2-女                              |
| phone     | string | 是   | 手机号                                        |
| job       | int    | 否   | 职位编码                                      |
| salary    | int    | 否   | 薪资                                          |
| image     | string | 否   | 头像 URL                                      |
| entryDate | string | 否   | 入职日期，`yyyy-MM-dd`                        |
| deptId    | int    | 是   | 所属部门 ID                                   |
| empList   | array  | 否   | 工作经历（**先删后增**，不传则不修改）        |

**empList 子字段**（同新增）

| 字段    | 类型   | 必填 | 说明                        |
|---------|--------|------|-----------------------------|
| company | string | 否   | 公司名称                    |
| job     | string | 否   | 职位                        |
| begin   | string | 否   | 开始日期，`yyyy-MM-dd`      |
| end     | string | 否   | 结束日期，`yyyy-MM-dd`      |

**请求示例**

```json
{
  "name": "李四（修改）",
  "gender": 2,
  "phone": "13900139001",
  "job": 3,
  "salary": 12000,
  "image": "https://example.com/avatar2_new.jpg",
  "entryDate": "2024-06-01",
  "deptId": 2,
  "empList": [
    {
      "company": "阿里巴巴股份有限公司",
      "job": "优酷业务线产品经理",
      "begin": "2019-02-01",
      "end": "2023-03-15"
    }
  ]
}
```

**响应示例**

```json
{
  "code": 1,
  "msg": "success",
  "data": null
}
```

---

### 3.4 批量删除员工

```
DELETE /emps/{ids}
```

**路径参数**

| 参数 | 类型   | 必填 | 说明                         |
|------|--------|------|------------------------------|
| ids  | string | 是   | 员工ID，多个以英文逗号分隔   |

**请求示例**

```
DELETE /emps/1,2,3
```

**响应示例**

```json
{
  "code": 1,
  "msg": "success",
  "data": null
}
```

> 删除员工时会**连带删除**对应的工作经历记录。单个员工删除传入单个 ID 即可（如 `/emps/1`）。

---

## 四、文件上传 `/upload`

### 4.1 上传头像

```
POST /upload
Content-Type: multipart/form-data
```

**请求参数（FormData）**

| 参数 | 类型 | 必填 | 说明                                      |
|------|------|------|-------------------------------------------|
| file | File | 是   | 图片文件，PNG/JPEG/JPG，大小 ≤ 2MB，建议 200×200 或 300×300 |

**响应示例**

```json
{
  "code": 1,
  "msg": "success",
  "data": "https://example.com/upload/avatar_20240601.jpg"
}
```

> `data` 为上传后的图片访问 URL，将其作为 `image` 字段传给新增/修改员工的接口即可完成头像绑定。

---

## 五、数据字典

### 5.1 性别（gender）

| 编码 | 名称 |
|------|------|
| 1    | 男   |
| 2    | 女   |

### 5.2 职位（job）

| 编码 | 名称     |
|------|----------|
| 1    | 班主任   |
| 2    | 讲师     |
| 3    | 学工主管 |
| 4    | 教研主管 |
| 5    | 咨询师   |

---

## 六、错误码

| code | 说明     | 典型场景               |
|------|----------|------------------------|
| 1    | 成功     | 所有操作正常完成       |
| 0    | 业务失败 | 参数校验不通过、数据冲突等 |

---

## 七、接口总览

| # | 方法   | 路径            | 说明               |
|---|--------|-----------------|--------------------|
| 1 | GET    | `/depts`        | 查询全部部门       |
| 2 | GET    | `/depts/{id}`   | 根据 ID 查询部门   |
| 3 | POST   | `/depts`        | 新增部门           |
| 4 | PUT    | `/depts/{id}`   | 修改部门           |
| 5 | DELETE | `/depts/{id}`   | 删除部门           |
| 6 | GET    | `/emps`         | 条件分页查询员工   |
| 7 | POST   | `/emps`         | 新增员工           |
| 8 | PUT    | `/emps/{id}`    | 修改员工           |
| 9 | DELETE | `/emps/{ids}`   | 批量删除员工       |
|10 | POST   | `/upload`       | 上传头像文件       |

---

## 八、附录

1. 新增/修改员工时会自动维护 `createTime` 与 `updateTime` 时间戳
2. 员工姓名查询为**模糊匹配**，大小写不敏感
3. 批量删除传入的多个 ID 以英文逗号分隔，**不含空格**
4. 修改员工时传入 `empList` 会**先删除原有经历再批量新增**；不传则保留原有经历不变
5. 头像上传成功后返回的是可访问的 **完整 URL**，可直接用于 `<img>` 标签
6. 开发环境 Vite 将 `/api/*` → `http://localhost:8080/*`，生产环境由 Nginx 代理