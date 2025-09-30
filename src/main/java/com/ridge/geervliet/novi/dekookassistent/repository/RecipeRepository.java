package com.ridge.geervliet.novi.dekookassistent.repository;

import com.ridge.geervliet.novi.dekookassistent.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {


    @Query("SELECT DISTINCT r FROM Recipe r JOIN r.recipeIngredients ri JOIN ri.ingredient i WHERE LOWER(i.name) IN :ingredientNames")

    List<Recipe> findByIngredientNames(@Param("ingredientNames") List<String> ingredientNames);
}