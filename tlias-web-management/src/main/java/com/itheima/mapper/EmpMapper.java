package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

    /**
     * 新增员工
     */
    @Insert("insert into emp(username, password, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values(#{username}, #{password}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Emp emp);

    /**
     * 更新员工
     */
    @Update("update emp set name=#{name}, gender=#{gender}, phone=#{phone}, job=#{job}, salary=#{salary}, " +
            "image=#{image}, entry_date=#{entryDate}, dept_id=#{deptId}, update_time=#{updateTime} where id=#{id}")
    void update(Emp emp);

    /**
     * 条件查询员工信息
     */
    List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * 批量删除员工
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 按职位统计员工人数
     */
    List<Map<String, Object>> countEmpJobData();

    /**
     * 统计员工性别人数
     */
    List<Map<String, Object>> countEmpGenderData();
}
