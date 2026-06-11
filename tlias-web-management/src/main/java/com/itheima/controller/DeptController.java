package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")//抽取路径
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result list(){
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        //System.out.println("根据id查询部门："+id);
        log.info("根据id查找数据:{}",id);
        Dept dept = deptService.findById(id);
        return Result.success(dept);
    }

    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("新增部门:{}",dept);
        deptService.add(dept);
        return Result.success(null);
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable Integer id, @RequestBody Dept dept){
        dept.setId(id);
        log.info("修改部门:{}",dept);
        deptService.update(dept);
        return Result.success(null);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门:{}",id);
        deptService.delete(id);
        return Result.success(null);
    }

}
