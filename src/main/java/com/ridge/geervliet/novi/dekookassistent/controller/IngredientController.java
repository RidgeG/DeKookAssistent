package com.ridge.geervliet.novi.dekookassistent.controller;

import com.ridge.geervliet.novi.dekookassistent.dto.Dto.IngredientDto;
import com.ridge.geervliet.novi.dekookassistent.dto.input.IngredientInputDto;
import com.ridge.geervliet.novi.dekookassistent.mapper.IngredientMapper;
import com.ridge.geervliet.novi.dekookassistent.model.Ingredient;
import com.ridge.geervliet.novi.dekookassistent.service.IngredientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;
    private final IngredientMapper ingredientMapper;

    public IngredientController(IngredientService ingredientService, IngredientMapper ingredientMapper) {
        this.ingredientService = ingredientService;
        this.ingredientMapper = ingredientMapper;
    }

    @GetMapping
    public ResponseEntity<List<IngredientDto>> getAllIngredients() {
        List<Ingredient> ingredients = ingredientService.getAllIngredients();
        List<IngredientDto> dtos = ingredients.stream().map(ingredientMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientDto> getIngredientById(@PathVariable Long id) {
        Ingredient ingredient = ingredientService.getIngredientById(id);
        return ResponseEntity.ok(ingredientMapper.toDto(ingredient));
    }

    @PostMapping
    public ResponseEntity<IngredientDto> createIngredient(@Valid @RequestBody IngredientInputDto inputDto) {
        Ingredient newIngredient = ingredientService.createIngredient(inputDto);
        return new ResponseEntity<>(ingredientMapper.toDto(newIngredient), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteIngredient(id);
        return ResponseEntity.noContent().build();
    }
}