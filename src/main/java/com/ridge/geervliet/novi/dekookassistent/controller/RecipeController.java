package com.ridge.geervliet.novi.dekookassistent.controller;


import com.ridge.geervliet.novi.dekookassistent.dto.Dto.RecipeDto;
import com.ridge.geervliet.novi.dekookassistent.dto.input.RecipeInputDto;
import com.ridge.geervliet.novi.dekookassistent.mapper.RecipeMapper;
import com.ridge.geervliet.novi.dekookassistent.model.Recipe;
import com.ridge.geervliet.novi.dekookassistent.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeService recipeService;
    private final RecipeMapper recipeMapper;

    public RecipeController(RecipeService recipeService, RecipeMapper recipeMapper) {
        this.recipeService = recipeService;
        this.recipeMapper = recipeMapper;

    }

    @GetMapping
    public ResponseEntity<List<RecipeDto>> getAllRecipes() {
        List<Recipe> recipes = recipeService.getAllRecipes();
        List<RecipeDto> dtos = recipes.stream().map(recipeMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> getRecipeById(@PathVariable Long id) {
        Recipe recipe = recipeService.getRecipeById(id);
        return ResponseEntity.ok(recipeMapper.toDto(recipe));
    }

    @PostMapping
    public ResponseEntity<RecipeDto> createRecipe(@Valid @RequestBody RecipeInputDto inputDto, Authentication authentication) {
        String username = authentication.getName();
        Recipe newRecipe = recipeService.createRecipe(inputDto, username);
        return new ResponseEntity<>(recipeMapper.toDto(newRecipe), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {

        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }
}