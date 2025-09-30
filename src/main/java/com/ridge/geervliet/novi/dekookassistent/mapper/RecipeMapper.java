package com.ridge.geervliet.novi.dekookassistent.mapper;


import com.ridge.geervliet.novi.dekookassistent.dto.Dto.RecipeDto;
import com.ridge.geervliet.novi.dekookassistent.dto.Dto.RecipeIngredientDto;
import com.ridge.geervliet.novi.dekookassistent.dto.input.RecipeInputDto;
import com.ridge.geervliet.novi.dekookassistent.model.Recipe;
import com.ridge.geervliet.novi.dekookassistent.model.RecipeIngredient;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RecipeMapper {
    public Recipe toEntity(RecipeInputDto inputDto) {
        Recipe recipe = new Recipe();
        recipe.setTitle(inputDto.getTitle());
        recipe.setDescription(inputDto.getDescription());
        recipe.setServings(inputDto.getServings());
        return recipe;
    }

    public RecipeDto toDto(Recipe recipe) {
        RecipeDto dto = new RecipeDto();
        dto.setId(recipe.getId());
        dto.setTitle(recipe.getTitle());
        dto.setDescription(recipe.getDescription());
        dto.setServings(recipe.getServings());

        if (recipe.getOwner()!= null) {

            dto.setOwnerId(recipe.getOwner().getId());
            dto.setOwnerName(recipe.getOwner().getFirstName() + " " + recipe.getOwner().getLastName());
        }

        if (recipe.getCategory()!= null) {
            dto.setCategoryId(recipe.getCategory().getId());
            dto.setCategoryName(recipe.getCategory().getName());
        }

        if (recipe.getRecipeIngredients()!= null) {
            dto.setRecipeIngredients(recipe.getRecipeIngredients().stream()
                    .map(this::toRecipeIngredientDto)
                    .collect(Collectors.toList()));
        }
        if (recipe.getUploads()!= null) {
            dto.setUploadUrls(recipe.getUploads().stream()
                    .map(upload -> upload.getUrl())

                    .collect(Collectors.toList()));
        }

        return dto;
    }


    public RecipeIngredientDto toRecipeIngredientDto(RecipeIngredient recipeIngredient) {
        RecipeIngredientDto dto = new RecipeIngredientDto();
        if (recipeIngredient.getIngredient()!= null) {
            dto.setIngredientId(recipeIngredient.getIngredient().getId());
            dto.setIngredientName(recipeIngredient.getIngredient().getName());
        }
        dto.setQuantity(recipeIngredient.getQuantity());
        dto.setUnit(recipeIngredient.getUnit());
        return dto;
    }



}