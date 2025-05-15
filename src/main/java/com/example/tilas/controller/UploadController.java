package com.example.tilas.controller;

import com.example.tilas.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @PostMapping("/upload")
    // 注意这里的参数，用于文件上传
    public Result upload(MultipartFile image) throws IOException {
        log.info("用户上传图片");
        String newFile = UUID.randomUUID().toString();
        // 注意不能直接写"."，不然寄了
        String[] s = image.getOriginalFilename().split("\\.");
        String extension = s[s.length - 1];
        newFile = newFile + "." + extension;
        image.transferTo(new File("D:\\编程\\java\\tilas\\images\\" + newFile));
        return Result.success("D:\\编程\\java\\tilas\\images\\" + newFile);
    }

}
