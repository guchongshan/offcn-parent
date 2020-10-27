package com.offcn.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.*;

/**
 * @author 胡长生
 * @create 2020-10-24 15:54
 */
public class OSSTest {

    public static void main(String[] args) throws FileNotFoundException {

        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-shanghai.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4G5FcRVhwZtABoM3LFhF";
        String accessKeySecret = "UvMHuDUGXkSZNAkEJmQ48roJSu7k8u";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
// 上传文件到指定的存储空间（bucketName）并将其保存为指定的文件名称（objectName）。
        FileInputStream inputStream = new FileInputStream(new File("E:\\img\\2222.jpg"));
        ossClient.putObject("hk091","pic/222.jpg",inputStream);
// 关闭OSSClient。
        ossClient.shutdown();
        System.out.println("上传结束");
    }
}
