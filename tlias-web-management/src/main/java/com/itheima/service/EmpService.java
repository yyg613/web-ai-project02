package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface EmpService {
    /**
     * 条件分页查询
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 保存员工信息及工作经历
     */
    void save(Emp emp);

    /**
     * 更新员工信息
     */
    void update(Emp emp);

    /**
     * 批量删除员工
     */
    void delete(List<Integer> ids);
}
