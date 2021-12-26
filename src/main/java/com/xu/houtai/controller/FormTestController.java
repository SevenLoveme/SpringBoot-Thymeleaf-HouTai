package com.xu.houtai.controller;
/*文件上传*/

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
public class FormTestController {
    @GetMapping("/form_layouts")
    public String form_layouts(){
        return "/form/form_layouts";
    }

    /*MultipartFile 自动封装上传过来的文件*/
    @PostMapping("upload")
    public String upload(@RequestParam("email") String email,
                         @RequestParam("password") String password,
                         @RequestPart("headerImg") MultipartFile headerImg,
                         @RequestPart("photos") MultipartFile[] photos) throws IOException {


        log.info("上传的信息:email={},password={},headerImg={},photos={}",
                email,password,headerImg.getSize(),photos.length);
        if (!headerImg.isEmpty()){
            //保存到文件服务器,OSS服务器
            String filename = headerImg.getOriginalFilename();//文件名
            headerImg.transferTo(new File("D:\\java\\资料\\SpringBoot\\SpringBootDemo\\src\\main\\resources\\static\\images\\"+filename));
        }
        if (photos.length>0){
            for (MultipartFile photo : photos) {
                if (!photo.isEmpty()){
                    photo.transferTo(new File("D:\\java\\资料\\SpringBoot\\SpringBootDemo\\src\\main\\resources\\static\\images\\"+photo.getOriginalFilename()));
                }
            }
        }
        return "index";
    }

}
