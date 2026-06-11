# TLAS 员工管理系统 - API 接口文档

> 基础路径：`http://localhost:8080`

---

## 一、统一响应格式

所有接口返回统一的 JSON 格式：

```json
{
  "code": 1,       // 状态码：1-成功，0-失败
  "msg": "success", // 提示信息
  "data": {}        // 响应数据（成功时返回，失败时为 null）
}
```

### 分页响应格式

分页查询接口的 `data` 字段结构：

```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "total": 100,    // 总记录数
    "rows": []       // 当前页数据列表
  }
}
```

---

## 二、部门管理接口

### 2.1 查询全部部门

- **请求方式**：`GET`
- **请求路径**：`/depts`
- **请求参数**：无
- **响应示例**：

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

---

### 2.2 根据 ID 查询部门

- **请求方式**：`GET`
- **请求路径**：`/depts/{id}`
- **路径参数**：

| 参数名 | 类型    | 必填 | 说明   |
|--------|---------|------|--------|
| id     | Integer | 是   | 部门ID |

- **响应示例**：

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

- **请求方式**：`POST`
- **请求路径**：`/depts`
- **请求头**：`Content-Type: application/json`
- **请求体**：

| 参数名 | 类型   | 必填 | 说明     |
|--------|--------|------|----------|
| name   | String | 是   | 部门名称 |

- **请求示例**：

```json
{
  "name": "市场部"
}
```

- **响应示例**：

```json
{
  "code": 1,
  "msg": "success",
  "data": null
}
```

---

### 2.4 修改部门

- **请求方式**：`PUT`
- **请求路径**：`/depts/{id}`
- **路径参数**：

| 参数名 | 类型    | 必填 | 说明   |
|--------|---------|------|--------|
| id     | Integer | 是   | 部门ID |

- **请求头**：`Content-Type: application/json`
- **请求体**：

| 参数名 | 类型   | 必填 | 说明     |
|--------|--------|------|----------|
| name   | String | 是   | 部门名称 |

- **请求示例**：

```json
{
  "name": "市场部（改名）"
}
```

- **响应示例**：

```json
{
  "code": 1,
  "msg": "success",
  "data": null
}
```

---

### 2.5 删除部门

- **请求方式**：`DELETE`
- **请求路径**：`/depts/{id}`
- **路径参数**：

| 参数名 | 类型    | 必填 | 说明   |
|--------|---------|------|--------|
| id     | Integer | 是   | 部门ID |

- **响应示例**：

```json
{
  "code": 1,
  "msg": "success",
  "data": null
}
```

---

## 三、员工管理接口

### 3.1 条件分页查询员工

- **请求方式**：`GET`
- **请求路径**：`/emps`
- **请求参数（Query）**：

| 参数名   | 类型     | 必填 | 默认值 | 说明                                      |
|----------|----------|------|--------|-------------------------------------------|
| page     | Integer  | 否   | 1      | 当前页码                                  |
| pageSize | Integer  | 否   | 10     | 每页记录数                                |
| name     | String   | 否   | null   | 员工姓名（模糊查询）                      |
| gender   | Integer  | 否   | null   | 性别（1:男, 2:女）                        |
| begin    | String   | 否   | null   | 入职日期-开始（格式：yyyy-MM-dd）         |
| end      | String   | 否   | null   | 入职日期-结束（格式：yyyy-MM-dd）         |

- **请求示例**：

```
GET /emps?page=1&pageSize=10&name=张&gender=1&begin=2024-01-01&end=2024-12-31
```

- **响应示例**：

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

---

### 3.2 新增员工

- **请求方式**：`POST`
- **请求路径**：`/emps`
- **请求头**：`Content-Type: application/json`
- **请求体**：

| 参数名    | 类型           | 必填 | 说明                                          |
|-----------|----------------|------|-----------------------------------------------|
| username  | String         | 是   | 用户名                                        |
| password  | String         | 是   | 密码                                          |
| name      | String         | 是   | 姓名                                          |
| gender    | Integer        | 是   | 性别（1:男, 2:女）                            |
| phone     | String         | 否   | 手机号                                        |
| job       | Integer        | 否   | 职位（1:班主任, 2:讲师, 3:学工主管, 4:教研主管, 5:咨询师） |
| salary    | Integer        | 否   | 薪资                                          |
| image     | String         | 否   | 头像 URL                                      |
| entryDate | String         | 否   | 入职日期（格式：yyyy-MM-dd）                  |
| deptId    | Integer        | 是   | 关联部门 ID                                   |
| empList   | Array\<Object\>| 否   | 工作经历列表                                  |

**empList 工作经历对象结构**：

| 参数名  | 类型   | 必填 | 说明                        |
|---------|--------|------|-----------------------------|
| company | String | 否   | 公司名称                    |
| job     | String | 否   | 职位                        |
| begin   | String | 否   | 开始日期（格式：yyyy-MM-dd）|
| end     | String | 否   | 结束日期（格式：yyyy-MM-dd）|

- **请求示例**：

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

- **响应示例**：

```json
{
  "code": 1,
  "msg": "success",
  "data": null
}
```

---

### 3.3 修改员工

- **请求方式**：`PUT`
- **请求路径**：`/emps/{id}`
- **路径参数**：

| 参数名 | 类型    | 必填 | 说明   |
|--------|---------|------|--------|
| id     | Integer | 是   | 员工ID |

- **请求头**：`Content-Type: application/json`
- **请求体**：

| 参数名    | 类型           | 必填 | 说明                                          |
|-----------|----------------|------|-----------------------------------------------|
| name      | String         | 是   | 姓名                                          |
| gender    | Integer        | 是   | 性别（1:男, 2:女）                            |
| phone     | String         | 否   | 手机号                                        |
| job       | Integer        | 否   | 职位（1:班主任, 2:讲师, 3:学工主管, 4:教研主管, 5:咨询师） |
| salary    | Integer        | 否   | 薪资                                          |
| image     | String         | 否   | 头像 URL                                      |
| entryDate | String         | 否   | 入职日期（格式：yyyy-MM-dd）                  |
| deptId    | Integer        | 是   | 关联部门 ID                                   |
| empList   | Array\<Object\>| 否   | 工作经历列表（传入时先删除原有经历再新增）    |

**empList 工作经历对象结构**：

| 参数名  | 类型   | 必填 | 说明                        |
|---------|--------|------|-----------------------------|
| company | String | 否   | 公司名称                    |
| job     | String | 否   | 职位                        |
| begin   | String | 否   | 开始日期（格式：yyyy-MM-dd）|
| end     | String | 否   | 结束日期（格式：yyyy-MM-dd）|

- **请求示例**：

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

- **响应示例**：

```json
{
  "code": 1,
  "msg": "success",
  "data": null
}
```

---

### 3.4 批量删除员工

- **请求方式**：`DELETE`
- **请求路径**：`/emps/{ids}`
- **路径参数**：

| 参数名 | 类型           | 必填 | 说明               |
|--------|----------------|------|--------------------|
| ids    | Array\<Integer\>| 是  | 员工ID列表（逗号分隔）|

- **请求示例**：

```
DELETE /emps/1,2,3
```

- **响应示例**：

```json
{
  "code": 1,
  "msg": "success",
  "data": null
}
```

---

### 3.5 文件上传

- **请求方式**：`POST`
- **请求路径**：`/upload`
- **请求头**：`Content-Type: multipart/form-data`
- **请求参数**：

| 参数名 | 类型 | 必填 | 说明                                          |
|--------|------|------|-----------------------------------------------|
| file   | File | 是   | 图片文件（仅限 PNG/JPEG/JPG，大小不超过 2MB） |

- **响应示例**：

```json
{
  "code": 1,
  "msg": "success",
  "data": "https://example.com/upload/avatar_20240601.jpg"
}
```

> `data` 字段为图片的访问 URL，前端会将其存储到员工的 `image` 字段。

---

## 四、数据字典

### 4.1 性别（gender）

| 值 | 说明 |
|----|------|
| 1  | 男   |
| 2  | 女   |

### 4.2 职位（job）

| 值 | 说明     |
|----|----------|
| 1  | 班主任   |
| 2  | 讲师     |
| 3  | 学工主管 |
| 4  | 教研主管 |
| 5  | 咨询师   |

---

## 五、错误码说明

| code | 说明 |
|------|------|
| 1    | 操作成功 |
| 0    | 操作失败 |

---

## 六、接口总览

| 请求方式 | 接口路径            | 功能说明         |
|----------|---------------------|------------------|
| GET      | /depts              | 查询全部部门     |
| GET      | /depts/{id}         | 根据ID查询部门   |
| POST     | /depts              | 新增部门         |
| PUT      | /depts/{id}         | 修改部门         |
| DELETE   | /depts/{id}         | 删除部门         |
| GET      | /emps               | 条件分页查询员工 |
| POST     | /emps               | 新增员工         |
| PUT      | /emps/{id}          | 修改员工         |
| DELETE   | /emps/{ids}         | 批量删除员工     |
| POST     | /upload             | 文件上传（头像） |

---

## 七、注意事项

1. 所有时间字段格式为 `yyyy-MM-dd'T'HH:mm:ss`（LocalDateTime）或 `yyyy-MM-dd`（LocalDate）
2. 分页查询默认第 1 页，每页 10 条记录
3. 员工姓名查询为模糊匹配
4. 批量删除员工时，会同步删除对应的工作经历记录
5. 新增员工时会自动填充 `createTime` 和 `updateTime`
6. 修改员工时会自动更新 `updateTime`
7. 修改员工时如果传入 `empList`，会先删除原有工作经历再批量新增
8. 文件上传接口仅支持 PNG/JPEG/JPG 格式，大小不超过 2MB
