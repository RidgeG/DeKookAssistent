package com.ridge.geervliet.novi.dekookassistent.service;

import com.ridge.geervliet.novi.dekookassistent.exception.ResourceNotFoundException;
import com.ridge.geervliet.novi.dekookassistent.model.Upload;
import com.ridge.geervliet.novi.dekookassistent.repository.UploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class UploadService {

    @Value("${file.upload-dir}")
    private String uploadDir;
    private final UploadRepository uploadRepository;

    @Autowired
    public UploadService(UploadRepository uploadRepository) {
        this.uploadRepository = uploadRepository;
    }

    public Upload uploadFile(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(uploadPath);

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileExtension = "";
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0) {
            fileExtension = fileName.substring(dotIndex);
        }
        String newFileName = UUID.randomUUID().toString() + fileExtension;
        Path targetLocation = uploadPath.resolve(newFileName);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        Upload upload = new Upload();
        upload.setUrl("/uploads/" + newFileName);
        upload.setPublicId(newFileName);
        upload.setResourceType(file.getContentType());
        upload.setFormat(fileExtension.substring(1));
        return uploadRepository.save(upload);
    }

    public void deleteUpload(Long id) {
        Upload upload = uploadRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Upload not found with id " + id));
        Path filePath = Paths.get(uploadDir).toAbsolutePath().normalize().resolve(upload.getPublicId());
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete file from local storage", e);
        }
        uploadRepository.delete(upload);
    }
}