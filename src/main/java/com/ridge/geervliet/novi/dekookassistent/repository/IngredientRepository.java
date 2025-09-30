package com.ridge.geervliet.novi.dekookassistent.repository;

import com.ridge.geervliet.novi.dekookassistent.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findByNameIn(List<String> names);

}