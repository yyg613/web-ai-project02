package com.itheima.service;

import com.itheima.pojo.EmpLog;

public interface EmpLogService {

    /**
     * 记录操作日志
     * @param empLog 日志对象
     */
    public void insertLog(EmpLog empLog);
}
