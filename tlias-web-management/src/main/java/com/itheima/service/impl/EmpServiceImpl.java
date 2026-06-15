package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpLogMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpLogService;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        // 1. 分页查询员工基本信息
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        List<Emp> rows = empMapper.list(empQueryParam);
        PageInfo<Emp> pageInfo = new PageInfo<>(rows);

        // 2. 批量查询工作经历并填充（回显）
        if (!CollectionUtils.isEmpty(rows)) {
            List<Integer> empIds = rows.stream()
                    .map(Emp::getId)
                    .toList();
            List<EmpExpr> allExprs = empExprMapper.selectByEmpIds(empIds);

            // 按 empId 分组，填充到对应员工
            var exprMap = allExprs.stream()
                    .collect(java.util.stream.Collectors.groupingBy(EmpExpr::getEmpId));
            rows.forEach(emp -> emp.setEmpList(exprMap.get(emp.getId())));
        }

        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }
    @Transactional(rollbackFor = {Exception.class}) //事务管理
    @Override
    public void save(Emp emp) {
        // 1. 保存员工基本信息
        try {
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            // 2. 保存员工工作经历
            List<EmpExpr> exprList = emp.getEmpList();
            if (!CollectionUtils.isEmpty(exprList)) {
                exprList.forEach(expr -> expr.setEmpId(emp.getId()));
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //记录操作日志
            EmpLog empLog = new EmpLog(null,LocalDateTime.now(),"新增员工"+emp);
            empLogService.insertLog(empLog);

        }


    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        // 1. 更新员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);

        // 2. 更新工作经历：先删除原有经历，再批量新增
        List<EmpExpr> exprList = emp.getEmpList();
        if (exprList != null) {
            // 删除该员工的原有工作经历
            empExprMapper.deleteByEmpIds(List.of(emp.getId()));

            // 如果有新的工作经历，则批量插入
            if (!CollectionUtils.isEmpty(exprList)) {
                exprList.forEach(expr -> expr.setEmpId(emp.getId()));
                empExprMapper.insertBatch(exprList);
            }
        }
    }

    @Transactional(rollbackFor = {Exception.class})//获取到生成的主键--主键返回
    @Override
    public void delete(List<Integer> ids) {
        //批量删除员工基本信息
        empMapper.deleteByIds(ids);
        //批量删除员工工作信息
        empExprMapper.deleteByEmpIds(ids);
    }

}
