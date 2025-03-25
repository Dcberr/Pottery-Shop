package com.project.potteryshop.Service;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.potteryshop.Entity.Image;
import com.project.potteryshop.Entity.Product;
import com.project.potteryshop.Repository.ImageRepository;
import com.project.potteryshop.Repository.ProductRepository;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MinioService {
    private final MinioClient minioClient;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ProductRepository productRepository;

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

    // public String uploadFile(MultipartFile file) {
    // try {
    // String fileName = file.getOriginalFilename();
    // InputStream fileStream = file.getInputStream();

    // boolean found =
    // minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    // if (!found) {
    // minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
    // }

    // minioClient.putObject(
    // PutObjectArgs.builder()
    // .bucket(bucketName)
    // .object(fileName)
    // .stream(fileStream, file.getSize(), -1)
    // .contentType(file.getContentType())
    // .build());

    // return fileName;
    // } catch (IOException | InvalidKeyException | ErrorResponseException |
    // InsufficientDataException
    // | InternalException | InvalidResponseException | NoSuchAlgorithmException |
    // XmlParserException
    // | IllegalArgumentException | io.minio.errors.ErrorResponseException |
    // io.minio.errors.InternalException
    // | io.minio.errors.ServerException e) {
    // throw new RuntimeException("Upload error: " + e.getMessage());
    // }
    // }

    public List<String> uploadFiles(List<MultipartFile> files, String productId) {
        List<String> filesName = files.stream().map(file -> {
            try (InputStream inputStream = file.getInputStream()) {
                minioClient.putObject(
                        PutObjectArgs.builder()
                                .bucket(bucketName)
                                .object(file.getOriginalFilename())
                                .stream(inputStream, file.getSize(), -1)
                                .contentType(file.getContentType())
                                .build());
                return "Uploaded: " + file.getOriginalFilename();
            } catch (Exception e) {
                return "Failed: " + file.getOriginalFilename() + " -> " + e.getMessage();
            }
        }).collect(Collectors.toList());

        Product product = productRepository.findById(productId).orElseThrow();
        Image image = product.getImage();
        // Product product = productRepository.findById(productId).orElseThrow();
        image.setProductId(productId);
        image.setLinkImage(filesName);
        imageRepository.save(image);
        return filesName;
    }

    public String getFile(String fileName) {
        try {
            // Image image = new Image();
            String linkImage = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .method(Method.GET)
                            .build());
            // image.setLinkImage(fileName);
            // imageRepository.save(image);
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

    public Image getImage(String imageId) {
        return imageRepository.findById(imageId).orElseThrow();
    }
}
