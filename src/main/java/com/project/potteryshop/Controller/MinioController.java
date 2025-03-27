package com.project.potteryshop.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.potteryshop.Dto.ApiResponse;
import com.project.potteryshop.Entity.Image;
import com.project.potteryshop.Service.MinioService;

@RestController
@RequestMapping("/images")
public class MinioController {
    @Autowired
    private MinioService minioService;

    @PostMapping
    public ApiResponse<List<String>> UploadImage(@RequestParam("files") List<MultipartFile> files,
            @RequestParam String productId) {
        List<String> fileName = minioService.uploadFiles(files, productId).stream().map(minioService::getFile).toList();
        // Chuyển danh sách fileNames thành danh sách URL
        // List<String> fileUrls = fileName.stream()
        // .map(minioService::getFile) // Áp dụng getFile() cho từng phần tử
        // .toList();

        return ApiResponse.<List<String>>builder()
                .code(200)
                .message("Uploaded Image Successful!!!")
                .result(fileName)
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

    @GetMapping("/{imageId}")
    public ApiResponse<Image> getImage(@PathVariable String imageId) {
        return ApiResponse.<Image>builder()
                .code(200)
                .message("Get Image " + imageId + "Successful!!!")
                .result(minioService.getImage(imageId))
                .build();
    }
}
