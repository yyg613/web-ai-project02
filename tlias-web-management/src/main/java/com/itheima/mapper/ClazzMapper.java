package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClazzMapper {

    /**
     * 查询全部班级
     */
    List<Clazz> findAll();

    /**
     * 条件分页查询班级
     */
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

}
