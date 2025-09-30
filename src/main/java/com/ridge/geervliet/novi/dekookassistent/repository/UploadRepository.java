package com.ridge.geervliet.novi.dekookassistent.repository;

import com.ridge.geervliet.novi.dekookassistent.model.Upload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadRepository extends JpaRepository<Upload, Long> {
}
