package com.ridge.geervliet.novi.dekookassistent.mapper;

import com.ridge.geervliet.novi.dekookassistent.dto.Dto.IngredientDto;
import com.ridge.geervliet.novi.dekookassistent.dto.input.IngredientInputDto;
import com.ridge.geervliet.novi.dekookassistent.model.Ingredient;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapper {

    public IngredientDto toDto(Ingredient ingredient) {
        IngredientDto dto = new IngredientDto();
        dto.setId(ingredient.getId());
        dto.setName(ingredient.getName());
        dto.setAllergies(ingredient.getAllergies());
        dto.setCategory(ingredient.getCategory());
        return dto;
    }

    public Ingredient toEntity(IngredientInputDto inputDto) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(inputDto.getName());
        ingredient.setAllergies(inputDto.getAllergies());
        ingredient.setCategory(inputDto.getCategory());
        return ingredient;
    }
}