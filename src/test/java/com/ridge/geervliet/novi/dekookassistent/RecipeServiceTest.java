package com.ridge.geervliet.novi.dekookassistent;


import com.ridge.geervliet.novi.dekookassistent.dto.input.RecipeInputDto;
import com.ridge.geervliet.novi.dekookassistent.dto.input.RecipeIngredientInputDto;
import com.ridge.geervliet.novi.dekookassistent.exception.ResourceNotFoundException;
import com.ridge.geervliet.novi.dekookassistent.model.*;
import com.ridge.geervliet.novi.dekookassistent.repository.CategoryRepository;
import com.ridge.geervliet.novi.dekookassistent.repository.IngredientRepository;
import com.ridge.geervliet.novi.dekookassistent.repository.RecipeRepository;
import com.ridge.geervliet.novi.dekookassistent.repository.UploadRepository;
import com.ridge.geervliet.novi.dekookassistent.service.RecipeService;
import com.ridge.geervliet.novi.dekookassistent.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private UserService userService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private IngredientRepository ingredientRepository;

    @Mock
    private UploadRepository uploadRepository;

    @InjectMocks
    private RecipeService recipeService;

    @Test
    @DisplayName("getAllRecipes: should return list from repository")
    void getAllRecipes_returnsList() {
        Recipe r1 = new Recipe(); r1.setTitle("Recipe 1");
        Recipe r2 = new Recipe(); r2.setTitle("Recipe 2");
        when(recipeRepository.findAll()).thenReturn(List.of(r1, r2));

        List<Recipe> result = recipeService.getAllRecipes();

        assertEquals(2, result.size());
        assertTrue(result.contains(r1));
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("getRecipeById: when found should return recipe")
    void getRecipeById_found() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setTitle("Test Recipe");
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));

        Recipe result = recipeService.getRecipeById(1L);

        assertEquals(recipe, result);
        verify(recipeRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("getRecipeById: when not found should throw ResourceNotFoundException")
    void getRecipeById_notFound() {
        when(recipeRepository.findById(99L)).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class,
                () -> recipeService.getRecipeById(99L));

        assertTrue(ex.getMessage().contains("Recipe not found with id 99"));
    }

    @Test
    @DisplayName("createRecipe: should create recipe with all dependencies")
    void createRecipe_createsWithAllDependencies() {
        // Arrange
        String username = "testuser";
        RecipeInputDto inputDto = new RecipeInputDto();
        inputDto.setTitle("Test Recipe");
        inputDto.setDescription("Test Description");
        inputDto.setServings(4);
        inputDto.setCategoryId(1L);

        RecipeIngredientInputDto ingredientInput = new RecipeIngredientInputDto();
        ingredientInput.setIngredientId(10L);
        ingredientInput.setQuantity(2);
        ingredientInput.setUnit("cups");
        inputDto.setRecipeIngredients(List.of(ingredientInput));

        inputDto.setUploadIds(List.of(100L, 101L));

        User user = new User();
        user.setUsername(username);

        Category category = new Category();
        category.setId(1L);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(10L);
        ingredient.setName("Flour");

        Upload upload1 = new Upload();
        upload1.setId(100);
        Upload upload2 = new Upload();
        upload2.setId(101);

        when(userService.findByUsername(username)).thenReturn(user);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(ingredientRepository.findById(10L)).thenReturn(Optional.of(ingredient));
        when(uploadRepository.findById(100L)).thenReturn(Optional.of(upload1));
        when(uploadRepository.findById(101L)).thenReturn(Optional.of(upload2));

        Recipe savedRecipe = new Recipe();
        savedRecipe.setId(50L);
        when(recipeRepository.save(any(Recipe.class))).thenReturn(savedRecipe);

        // Act
        Recipe result = recipeService.createRecipe(inputDto, username);

        // Assert
        assertNotNull(result);
        assertEquals(50L, result.getId());

        verify(userService, times(1)).findByUsername(username);
        verify(categoryRepository, times(1)).findById(1L);
        verify(ingredientRepository, times(1)).findById(10L);
        verify(uploadRepository, times(1)).findById(100L);
        verify(uploadRepository, times(1)).findById(101L);
        verify(recipeRepository, times(2)).save(any(Recipe.class));
    }

    @Test
    @DisplayName("createRecipe: when category not found should throw ResourceNotFoundException")
    void createRecipe_categoryNotFound() {
        String username = "testuser";
        RecipeInputDto inputDto = new RecipeInputDto();
        inputDto.setCategoryId(999L);
        inputDto.setRecipeIngredients(List.of());
        inputDto.setUploadIds(List.of());

        User user = new User();
        when(userService.findByUsername(username)).thenReturn(user);
        when(categoryRepository.findById(999L)).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class,
                () -> recipeService.createRecipe(inputDto, username));

        assertTrue(ex.getMessage().contains("Category not found with id 999"));
    }

    @Test
    @DisplayName("createRecipe: when ingredient not found should throw ResourceNotFoundException")
    void createRecipe_ingredientNotFound() {
        String username = "testuser";
        RecipeInputDto inputDto = new RecipeInputDto();
        inputDto.setCategoryId(1L);

        RecipeIngredientInputDto ingredientInput = new RecipeIngredientInputDto();
        ingredientInput.setIngredientId(999L);
        inputDto.setRecipeIngredients(List.of(ingredientInput));
        inputDto.setUploadIds(List.of());

        User user = new User();
        Category category = new Category();

        when(userService.findByUsername(username)).thenReturn(user);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(ingredientRepository.findById(999L)).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class,
                () -> recipeService.createRecipe(inputDto, username));

        assertTrue(ex.getMessage().contains("Ingredient not found with id 999"));
    }

    @Test
    @DisplayName("createRecipe: when upload not found should throw ResourceNotFoundException")
    void createRecipe_uploadNotFound() {
        String username = "testuser";
        RecipeInputDto inputDto = new RecipeInputDto();
        inputDto.setCategoryId(1L);
        inputDto.setRecipeIngredients(List.of());
        inputDto.setUploadIds(List.of(999L));

        User user = new User();
        Category category = new Category();

        when(userService.findByUsername(username)).thenReturn(user);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(uploadRepository.findById(999L)).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class,
                () -> recipeService.createRecipe(inputDto, username));

        assertTrue(ex.getMessage().contains("Upload not found with id 999"));
    }

    @Test
    @DisplayName("deleteRecipe: when exists should call delete")
    void deleteRecipe_exists() {
        when(recipeRepository.existsById(5L)).thenReturn(true);

        recipeService.deleteRecipe(5L);

        verify(recipeRepository, times(1)).deleteById(5L);
    }

    @Test
    @DisplayName("deleteRecipe: when not exists should throw ResourceNotFoundException")
    void deleteRecipe_notExists() {
        when(recipeRepository.existsById(99L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> recipeService.deleteRecipe(99L));
        verify(recipeRepository, never()).deleteById(anyLong());
    }
}