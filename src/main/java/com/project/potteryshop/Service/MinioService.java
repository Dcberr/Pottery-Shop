package com.project.potteryshop.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.multipart.MultipartFile;

import com.project.potteryshop.Entity.Image;
import com.project.potteryshop.Repository.ImageRepository;

import io.minio.BucketExistsArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.XmlParserException;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MinioService {
    private final MinioClient minioClient;
    @Autowired
    private ImageRepository imageRepository;

    @Value("${minio.bucketName}")
    private String bucketName;

    public MinioService(@Value("${minio.url}") String url,
            @Value("${minio.accessKey}") String accessKey,
            @Value("${minio.secretKey}") String secretKey) {
        this.minioClient = MinioClient.builder()
                .endpoint(url)
                .credentials(accessKey, secretKey)
                .build();
    }

    public String uploadFile(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            InputStream fileStream = file.getInputStream();

            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .stream(fileStream, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());

            return fileName;
        } catch (IOException | InvalidKeyException | ErrorResponseException | InsufficientDataException
                | InternalException | InvalidResponseException | NoSuchAlgorithmException | XmlParserException
                | IllegalArgumentException | io.minio.errors.ErrorResponseException | io.minio.errors.InternalException
                | io.minio.errors.ServerException e) {
            throw new RuntimeException("Upload error: " + e.getMessage());
        }
    }

    // public List<String> uploadFiles(List<MultipartFile> files) {
    // return files.stream().map(file -> {
    // try (InputStream inputStream = file.getInputStream()) {
    // minioClient.putObject(
    // PutObjectArgs.builder()
    // .bucket(bucketName)
    // .object(file.getOriginalFilename())
    // .stream(inputStream, file.getSize(), -1)
    // .contentType(file.getContentType())
    // .build());
    // return "Uploaded: " + file.getOriginalFilename();
    // } catch (Exception e) {
    // return "Failed: " + file.getOriginalFilename() + " -> " + e.getMessage();
    // }
    // }).collect(Collectors.toList());
    // }

    public String getFile(String fileName) {
        try {
            Image image = new Image();
            String linkImage = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .method(Method.GET)
                            .build());
            image.setLinkImage(fileName);
            imageRepository.save(image);
            return linkImage;

        } catch (Exception e) {
            throw new RuntimeException("Get URL error: " + e.getMessage());
        }
    }

    public void deleteFile(String fileName) {
        try {
            Image image = new Image();

            image = imageRepository.findImageByLinkImage(fileName);
            imageRepository.delete(image);
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .build());

        } catch (Exception e) {
            throw new RuntimeException("Delete file error: " + e.getMessage());
        }
    }
}
