package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliOssOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliOssOperator aliOssOperator;

    // 允许的文件格式
    private static final List<String> ALLOWED_TYPES = Arrays.asList(
            "image/png", "image/jpeg", "image/jpg"
    );

    // 最大文件大小：2MB
    private static final long MAX_FILE_SIZE = 2 * 1024 * 1024;

    /**
     * 文件上传接口 - 上传到阿里云OSS
     * @param file 上传的文件
     * @return 返回OSS文件访问URL
     *
     *
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        log.info("上传文件: fileName={}, fileSize={}, contentType={}",
                file.getOriginalFilename(), file.getSize(), file.getContentType());

        // 1. 校验文件是否为空
        if (file == null || file.isEmpty()) {
            return Result.error("请选择要上传的文件");
        }

        // 2. 校验文件格式（仅支持 PNG/JPEG/JPG）
        String contentType = file.getContentType();
        if (contentType == null || !ALLOWED_TYPES.contains(contentType.toLowerCase())) {
            return Result.error("仅支持 PNG、JPEG、JPG 格式的图片");
        }

        // 3. 校验文件大小（不超过2MB）
        if (file.getSize() > MAX_FILE_SIZE) {
            return Result.error("文件大小不能超过2MB");
        }

        // 4. 获取原始文件名
        String originalFilename = file.getOriginalFilename();

        // 5. 调用OSS工具类上传文件，返回访问URL
        String url = aliOssOperator.upload(file.getBytes(), originalFilename);

        log.info("文件上传成功，访问URL: {}", url);

        // 6. 返回成功结果和URL
        return Result.success(url);
    }
}
