package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select id , name, create_time , update_time  from dept order by update_time desc;")
    @Results({
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    List<Dept> findAll();

    @Insert("insert into dept (name, create_time, update_time) values (#{name}, now(), now())")
    void add(Dept dept);

    @Update("update dept set name = #{name}, update_time =#{updateTime} where id = #{id}")
    void update(Dept dept);

    @Delete("delete from dept where id = #{id}")
    void delete(Integer id);

    @Update("set @i=0; update dept set id = (@i:=@i+1) order by id;")
    void resetIds();

    @Select("select id,name,create_time,update_time from dept where id = #{id}")
    @Results({
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    Dept findById(Integer id);

}
