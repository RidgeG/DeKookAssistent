package com.ridge.geervliet.novi.dekookassistent.mapper;

import com.ridge.geervliet.novi.dekookassistent.dto.Dto.UploadDto;
import com.ridge.geervliet.novi.dekookassistent.model.Upload;
import org.springframework.stereotype.Component;

@Component
public class UploadMapper {

    public UploadDto toDto(Upload upload) {
        UploadDto dto = new UploadDto();
        dto.setId((long) upload.getId());
        dto.setUrl(upload.getUrl());
        return dto;
    }

    public Upload toEntity(UploadDto dto) {
        Upload entity = new Upload();
        entity.setId(Math.toIntExact(dto.getId()));
        entity.setUrl(dto.getUrl());
        return entity;
    }
}