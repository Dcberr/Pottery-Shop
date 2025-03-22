package com.project.potteryshop.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.potteryshop.Dto.ApiResponse;
import com.project.potteryshop.Service.MinioService;

@RestController
@RequestMapping("/images")
public class MinioController {
    @Autowired
    private MinioService minioService;

    @PostMapping
    public ApiResponse<String> getUrlImage(@RequestParam("file") MultipartFile file) {
        String fileName = minioService.uploadFile(file);
        return ApiResponse.<String>builder()
                .code(200)
                .message("Get Image URL Successfull!!!")
                .result(minioService.getFile(fileName))
                .build();
    }

    @DeleteMapping
    public ApiResponse<Void> deleteImage(@RequestParam String fileName) {
        minioService.deleteFile(fileName);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("File deleted successfully: " + fileName)
                .build();
    }
}
