package com.YinnnH.controller;

import com.YinnnH.pojo.Result;
import com.YinnnH.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;
    @PostMapping("/message/file")
    public Result upload(@RequestParam("image") MultipartFile image) {
        if (image == null || image.isEmpty()) {
            log.error("上传的文件为空");
            return Result.error("上传的文件为空");
        }
        try {
            log.info("图片上传,图片名:{}", image.getOriginalFilename());
            String url = aliOSSUtils.upload(image);
            log.info("图片上传完成,图片路径:{}", url);
            return Result.success(url);
        } catch (Exception e) {
            log.error("图片上传过程中出现异常", e);
            return Result.error("图片上传失败，请稍后重试");
        }
    }

    @PostMapping("/user/avatar")
    public Result upload2(@RequestParam("image") MultipartFile image) {
        if (image == null || image.isEmpty()) {
            log.error("上传的头像为空");
            return Result.error("上传的头像为空");
        }
        try {
            log.info("头像上传,图片名:{}", image.getOriginalFilename());
            String url = aliOSSUtils.upload(image);
            log.info("图片上传完成,图片路径:{}", url);
            return Result.success(url);
        } catch (Exception e) {
            log.error("头像上传过程中出现异常", e);
            return Result.error("头像上传失败，请稍后重试");
        }
    }

}
