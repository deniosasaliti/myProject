package com.example.demo.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
public class AwsBucketService {




   final AmazonS3 amazonS3;

    public AwsBucketService(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public void upload( MultipartFile file, String bucketName,String fileName) {
        ObjectMetadata data = new ObjectMetadata();
        data.setContentType(file.getContentType());
        data.setContentLength(file.getSize());
        try {
            PutObjectResult objectResult = amazonS3.putObject(bucketName, fileName, file.getInputStream(), data);
        } catch (AmazonServiceException | IOException e) {
            throw new IllegalStateException("Failed to upload the file", e);
        }
    }

    public byte[] download(String path, String key) {
        try {
            S3Object object = amazonS3.getObject(path, key);
            S3ObjectInputStream objectContent = object.getObjectContent();
            return IOUtils.toByteArray(objectContent);
        } catch (AmazonServiceException | IOException e) {
            throw new IllegalStateException("Failed to download the file", e);
        }
    }

}
