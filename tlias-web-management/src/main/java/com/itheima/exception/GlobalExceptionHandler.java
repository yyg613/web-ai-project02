package com.itheima.exception;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 唯一约束冲突（如用户名重复）
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("唯一约束冲突: {}", e.getMessage());
        return Result.error("数据已存在，请勿重复提交");
    }

    /**
     * 数据完整性约束冲突（如外键约束、非空约束等）
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Result handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error("数据完整性约束冲突: {}", e.getMessage());
        return Result.error("数据操作失败，可能存在关联数据");
    }

    /**
     * SQL完整性约束冲突
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        log.error("SQL约束冲突: {}", e.getMessage());
        return Result.error("数据操作失败，违反数据库约束");
    }

    /**
     * 文件上传超出大小限制
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.error("文件上传超出大小限制: {}", e.getMessage());
        return Result.error("上传文件大小超出限制");
    }

    /**
     * 文件上传异常
     */
    @ExceptionHandler(MultipartException.class)
    public Result handleMultipartException(MultipartException e) {
        log.error("文件上传异常: {}", e.getMessage());
        return Result.error("文件上传失败");
    }

    /**
     * 请求体解析失败（如JSON格式错误）
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("请求体解析失败: {}", e.getMessage());
        return Result.error("请求参数格式错误");
    }

    /**
     * 运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Result handleRuntimeException(RuntimeException e) {
        log.error("运行时异常: {}", e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    /**
     * 兜底异常
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("系统异常: {}", e.getMessage(), e);
        return Result.error("系统繁忙，请稍后再试");
    }
}