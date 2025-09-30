package com.ridge.geervliet.novi.dekookassistent.service;


import com.ridge.geervliet.novi.dekookassistent.dto.input.RecipeInputDto;
import com.ridge.geervliet.novi.dekookassistent.exception.ResourceNotFoundException;
import com.ridge.geervliet.novi.dekookassistent.model.*;
import com.ridge.geervliet.novi.dekookassistent.repository.CategoryRepository;
import com.ridge.geervliet.novi.dekookassistent.repository.IngredientRepository;
import com.ridge.geervliet.novi.dekookassistent.repository.RecipeRepository;
import com.ridge.geervliet.novi.dekookassistent.repository.UploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserService userService;
    private final CategoryRepository categoryRepository;
    private final IngredientRepository ingredientRepository;
    private final UploadRepository uploadRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, UserService userService, CategoryRepository categoryRepository, IngredientRepository ingredientRepository, UploadRepository uploadRepository) {
        this.recipeRepository = recipeRepository;
        this.userService = userService;
        this.categoryRepository = categoryRepository;
        this.ingredientRepository = ingredientRepository;
        this.uploadRepository = uploadRepository;
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recipe not found with id " + id));
    }

    @Transactional
    public Recipe createRecipe(RecipeInputDto inputDto, String username) {
        User user = userService.findByUsername(username);

        Category category = categoryRepository.findById(inputDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + inputDto.getCategoryId()));

        Set<Upload> uploads = inputDto.getUploadIds().stream()
                .map(id -> uploadRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Upload not found with id " + id)))
                .collect(Collectors.toSet());

        Recipe recipe = new Recipe();
        recipe.setTitle(inputDto.getTitle());
        recipe.setDescription(inputDto.getDescription());
        recipe.setServings(inputDto.getServings());
        recipe.setOwner(user);
        recipe.setCategory(category);
        recipe.setUploads(uploads);

        Recipe savedRecipe = recipeRepository.save(recipe);

        Set<RecipeIngredient> recipeIngredients = inputDto.getRecipeIngredients().stream()
                .map(riDto -> {
                    Ingredient ingredient = ingredientRepository.findById(riDto.getIngredientId()).orElseThrow(() -> new ResourceNotFoundException("Ingredient not found with id " + riDto.getIngredientId()));
                    RecipeIngredient recipeIngredient = new RecipeIngredient();
                    recipeIngredient.setRecipe(savedRecipe);
                    recipeIngredient.setIngredient(ingredient);
                    recipeIngredient.setQuantity(riDto.getQuantity());
                    recipeIngredient.setUnit(riDto.getUnit());
                    return recipeIngredient;
                })
                .collect(Collectors.toSet());

        savedRecipe.setRecipeIngredients(recipeIngredients);
        return recipeRepository.save(savedRecipe);
    }

    public void deleteRecipe(Long id) {
        if (!recipeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recipe not found with id " + id);
        }
        recipeRepository.deleteById(id);
    }
}
