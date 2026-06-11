package com.itheima.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmpLog {
    private Integer id; //ID,主键
    private LocalDateTime operateTime; //操作时间
    private String info; //操作信息

    public EmpLog(Object o, LocalDateTime now, String s) {
    }
}