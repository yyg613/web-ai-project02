package com.itheima.mapper;

import com.itheima.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmpExprMapper {

    /**
     * 批量插入工作经历
     */
    void insertBatch(@Param("list") List<EmpExpr> list);

    /**
     * 根据员工ID批量删除工作经历
     */
    void deleteByEmpIds(@Param("empIds") List<Integer> empIds);

    /**
     * 根据员工ID列表批量查询工作经历（用于回显）
     */
    List<EmpExpr> selectByEmpIds(@Param("empIds") List<Integer> empIds);
}
