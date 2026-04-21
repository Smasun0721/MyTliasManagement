package org.example.tliaswebmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.tliaswebmanagement.pojo.Result;
import org.example.tliaswebmanagement.util.AliyunOSSOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UploadController {
    //--------本地存储------------
//    @PostMapping("/upload")
//    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
//        log.info("文件上传:{},{},{}",name,age,file);
//        //设置文件名
//        String originalFilename = file.getOriginalFilename();
//        String extense = originalFilename.substring(originalFilename.lastIndexOf("."));
//        File newFileName=new File("E:\\IT\\JavaProjects\\javaweb-project02\\Tlias-web-management\\src\\main\\resources\\static\\images\\"+ UUID.randomUUID()+extense);
//        //保存文件
//        file.transferTo(newFileName);
//        return Result.success();
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    //-------阿里云存储---------
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("上传文件:{}", file.getOriginalFilename());
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        return Result.success(url);
    }

}

