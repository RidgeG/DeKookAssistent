package com.ridge.geervliet.novi.dekookassistent.dto.input;




import jakarta.validation.constraints.*;

import java.util.List;

public class RecipeInputDto {
    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;


    private String description;

    @NotNull(message = "Servings is required")
    private int servings;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    @NotEmpty(message = "Ingredients list cannot be empty")
    private List<RecipeIngredientInputDto> recipeIngredients;

    private List<Long> uploadIds;

    // Getters and setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<RecipeIngredientInputDto> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<RecipeIngredientInputDto> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public List<Long> getUploadIds() {
        return uploadIds;
    }

    public void setUploadIds(List<Long> uploadIds) {
        this.uploadIds = uploadIds;

    }
}