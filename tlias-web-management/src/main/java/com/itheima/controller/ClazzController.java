package com.itheima.controller;

import com.itheima.mapper.DeptMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.Result;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @GetMapping
    public Result list(){
        log.info("查询全部班级数据");
        List<Clazz> clazzList= clazzService.findAll();
        return  Result.success(clazzList);
    }

}
