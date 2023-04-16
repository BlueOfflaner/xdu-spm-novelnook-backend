package com.xdu.nook.thirdware.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.aliyun.oss.model.PutObjectResult;
import com.xdu.nook.api.utils.R;
import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/oss")
public class OssController {
    @Resource
    OSS ossClient;

    @Value("${spring.cloud.alicloud.oss.endpoint}")
    private String endpoint;
    @Value("${spring.cloud.alicloud.oss.bucket}")
    private String bucket;
    @Value("${spring.cloud.alicloud.access-key}")
    private String accessId;


    // 获取当前oss策略
    @RequestMapping("/policy")
    public R policy() {
        String host = "https://" + bucket + "." + endpoint;
        // host的格式为 bucketname.endpoint

        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String dir = format + "/"; // 用户上传文件时指定的前缀。

        Map<String, String> respMap = null;
        try {
            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);

            respMap = new LinkedHashMap<String, String>();
            respMap.put("accessid", accessId);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
            // respMap.put("expire", formatISO8601Date(expiration));


        } catch (Exception e) {
            // Assert.fail(e.getMessage());
            e.printStackTrace();
        }

        return new R().put("data", respMap);
    }

    @PostMapping("/upload")
    R upload(MultipartFile file){
        try {
            // 创建OSSClient实例。

            InputStream inputStream = file.getInputStream();
            //获取文件真实名称
            String originalFilename = file.getOriginalFilename();
            //重命名，防止相同文件出现覆盖 生成的f4f2e1a3-391a-4d5a-9438-0c9f5d27708=》需要替换成f4f2e1a3391a4d5a94380c9f5d27708c
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            //新的文件名
            originalFilename=uuid+originalFilename;
            //2、把文件按照日期进行分类
            // 2021/6/30
            String datePath = LocalDate.now().toString();
            //拼接 021/6/30/1.jpg
            originalFilename=datePath+"/"+originalFilename;
            // oss实现上传文件
            //第一个参数：Bucket名称
            //第二个参数：上传到oss文件路径和文件名称 /zhz/avatar.txt
            ossClient.putObject(bucket, originalFilename, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();

            //把上传之后文件路径返回
            //需要把上传到阿里云oss路径手动拼接出来->https://zhz-mail.oss-cn-beijing.aliyuncs.com/WechatIMG19.jpeg
            String url="https://"+bucket+"."+endpoint+"/"+originalFilename;
            return R.ok(url);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
}
