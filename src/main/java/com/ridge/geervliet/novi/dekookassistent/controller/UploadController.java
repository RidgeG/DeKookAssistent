package com.ridge.geervliet.novi.dekookassistent.controller;


import com.ridge.geervliet.novi.dekookassistent.dto.Dto.UploadDto;
import com.ridge.geervliet.novi.dekookassistent.mapper.UploadMapper;
import com.ridge.geervliet.novi.dekookassistent.model.Upload;
import com.ridge.geervliet.novi.dekookassistent.service.UploadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/uploads")
public class UploadController {

    private final UploadService uploadService;
    private final UploadMapper uploadMapper;

    public UploadController(UploadService uploadService, UploadMapper uploadMapper) {
        this.uploadService = uploadService;
        this.uploadMapper = uploadMapper;
    }

    @PostMapping
    public ResponseEntity<UploadDto> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        Upload upload = uploadService.uploadFile(file);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(upload.getId()).toUri();
        return ResponseEntity.created(location).body(uploadMapper.toDto(upload));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUpload(@PathVariable Long id) {
        uploadService.deleteUpload(id);
        return ResponseEntity.noContent().build();
    }
}

