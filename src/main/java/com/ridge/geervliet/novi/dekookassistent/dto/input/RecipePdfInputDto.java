package com.ridge.geervliet.novi.dekookassistent.dto.input;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public class RecipePdfInputDto {
    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @NotEmpty(message = "Ingredients list cannot be empty")
    private List<@NotBlank(message = "Ingredient cannot be blank") String> ingredients;

    @NotBlank(message = "Instructions are required")
    @Size(min = 10, message = "Instructions must be at least 10 characters long")
    private String instructions;


    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}

