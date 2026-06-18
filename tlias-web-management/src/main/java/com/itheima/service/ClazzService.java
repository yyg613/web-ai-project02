package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface ClazzService {

    List<Clazz> findAll();

    /**
     * 条件分页查询
     */
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);
}
