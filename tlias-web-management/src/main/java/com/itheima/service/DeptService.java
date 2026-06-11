package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {

    List<Dept> findAll();

    void add(Dept dept);

    void update(Dept dept);

    void delete(Integer id);

    void resetIds();

    Dept findById(Integer id);

}
