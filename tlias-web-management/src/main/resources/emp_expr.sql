-- 创建工作经历表（如果不存在）
CREATE TABLE IF NOT EXISTS emp_expr (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    emp_id INT NOT NULL COMMENT '员工ID',
    company VARCHAR(100) COMMENT '公司名称',
    job VARCHAR(50) COMMENT '职位',
    begin DATE COMMENT '开始日期',
    end DATE COMMENT '结束日期',
    INDEX idx_emp_id (emp_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工工作经历表';