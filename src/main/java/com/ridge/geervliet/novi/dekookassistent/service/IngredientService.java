package com.ridge.geervliet.novi.dekookassistent.service;




import com.ridge.geervliet.novi.dekookassistent.dto.input.IngredientInputDto;
import com.ridge.geervliet.novi.dekookassistent.exception.ResourceNotFoundException;
import com.ridge.geervliet.novi.dekookassistent.model.Ingredient;
import com.ridge.geervliet.novi.dekookassistent.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient getIngredientById(Long id) {
        return ingredientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ingredient not found with id " + id));
    }

    public Ingredient createIngredient(IngredientInputDto inputDto) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(inputDto.getName());
        ingredient.setAllergies(inputDto.getAllergies());
        ingredient.setCategory(inputDto.getCategory());
        return ingredientRepository.save(ingredient);
    }

    public void deleteIngredient(Long id) {
        if (!ingredientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Ingredient not found with id " + id);
        }
        ingredientRepository.deleteById(id);

    }
}