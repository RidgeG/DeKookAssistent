package com.ridge.geervliet.novi.dekookassistent.dto.input;

import jakarta.validation.constraints.NotNull;

public class RecipeIngredientInputDto {
    @NotNull(message = "Ingredient ID is required")
    private Long ingredientId;

    private int quantity;

    private String unit;


    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}

