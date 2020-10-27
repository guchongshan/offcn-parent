package com.offcn.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import lombok.ToString;


import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author 胡长生
 * @create 2020-10-24 16:44
 */
@ToString
@Data
public class OssTemplate {

    /*
     endpoint: https://oss-cn-shanghai.aliyuncs.com
     bucketDomain: hk091.oss-cn-shanghai.aliyuncs.com
     accessKeyId: LTAI4G5FcRVhwZtABoM3LFhF
     accessKeySecret: UvMHuDUGXkSZNAkEJmQ48roJSu7k8u
     bucketName: hk901
  */

    private String endpoint="https://oss-cn-shanghai.aliyuncs.com";
    private String bucketDomain="hk091.oss-cn-shanghai.aliyuncs.com";
    private String accessKeyId="LTAI4G5FcRVhwZtABoM3LFhF";
    private String accessKeySecret="UvMHuDUGXkSZNAkEJmQ48roJSu7k8u";
    private String bucketName="hk091";

//    private String endpoint;
//    private String bucketDomain;
//    private String accessKeyId;
//    private String accessKeySecret;
//    private String bucketName;

    /**
     * 返回上传后的文件的访问路径
     *
     * @param inputStream
     * @param fileName
     * @return
     * @throws IOException
     */
    public String upload(InputStream inputStream, String fileName) {
        //1、加工文件夹和文件名
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String folderName = sdf.format(new Date());
        fileName = UUID.randomUUID().toString().replace("-", "") + "_" + fileName;
        //2、创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        //3、// 上传文件流，指定bucket的名称
        ossClient.putObject(bucketName, "pic/" + folderName + "/" + fileName, inputStream);

        //4、关闭资源
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ossClient.shutdown();
        String url = "https://" + bucketDomain + "/pic/" + folderName + "/" + fileName;
        System.out.println("上传文件访问路径:" + url);
        return url;
    }
}
