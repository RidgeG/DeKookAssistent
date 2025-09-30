package com.ridge.geervliet.novi.dekookassistent.repository;


import com.ridge.geervliet.novi.dekookassistent.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
