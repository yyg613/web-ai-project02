package com.itheima.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 阿里云OSS工具类
 */
@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliOssOperator {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String region;

    /**
     * 上传文件到OSS
     * @param file 本地文件
     * @return 文件访问URL
     */
    public String upload(File file) throws IOException {
        // 1. 生成唯一文件名
        String originalFilename = file.getName();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String objectName = UUID.randomUUID().toString() + extension;

        // 2. 将文件转成字节数组
        byte[] bytes = fileToByteArray(file);

        // 3. 创建OSS客户端
        OSS ossClient = createOssClient();

        try {
            // 4. 上传文件
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new ByteArrayInputStream(bytes));
            ossClient.putObject(putObjectRequest);

            // 5. 返回文件访问URL
            String url = "https://" + bucketName + "." + endpoint.replace("https://", "") + "/" + objectName;
            log.info("文件上传成功，访问URL: {}", url);
            return url;
        } catch (OSSException e) {
            log.error("OSS上传失败: {}", e.getMessage());
            throw new RuntimeException("文件上传失败: " + e.getMessage());
        } finally {
            // 6. 关闭OSS客户端
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    /**
     * 上传字节数组到OSS
     * @param bytes 字节数组
     * @param originalFilename 原始文件名（用于获取扩展名）
     * @return 文件访问URL
     */
    public String upload(byte[] bytes, String originalFilename) {
        // 1. 生成唯一文件名
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String objectName = UUID.randomUUID().toString() + extension;

        // 2. 创建OSS客户端
        OSS ossClient = createOssClient();

        try {
            // 3. 上传文件
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new ByteArrayInputStream(bytes));
            ossClient.putObject(putObjectRequest);

            // 4. 返回文件访问URL
            String url = "https://" + bucketName + "." + endpoint.replace("https://", "") + "/" + objectName;
            log.info("文件上传成功，访问URL: {}", url);
            return url;
        } catch (OSSException e) {
            log.error("OSS上传失败: {}", e.getMessage());
            throw new RuntimeException("文件上传失败: " + e.getMessage());
        } finally {
            // 5. 关闭OSS客户端
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    /**
     * 删除OSS文件
     * @param objectName 文件名（不含bucket名称）
     */
    public void delete(String objectName) {
        OSS ossClient = createOssClient();
        try {
            ossClient.deleteObject(bucketName, objectName);
            log.info("文件删除成功: {}", objectName);
        } catch (OSSException e) {
            log.error("OSS删除失败: {}", e.getMessage());
            throw new RuntimeException("文件删除失败: " + e.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    /**
     * 创建OSS客户端
     */
    private OSS createOssClient() {
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * 将文件转成字节数组
     */
    private byte[] fileToByteArray(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] bytes = new byte[(int) file.length()];
            fis.read(bytes);
            return bytes;
        }
    }
}
