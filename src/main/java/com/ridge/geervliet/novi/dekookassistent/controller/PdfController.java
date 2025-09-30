package com.ridge.geervliet.novi.dekookassistent.controller;

import com.ridge.geervliet.novi.dekookassistent.model.Recipe;
import com.ridge.geervliet.novi.dekookassistent.service.PdfGeneratorService;
import com.ridge.geervliet.novi.dekookassistent.service.RecipeService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/generate-pdf")
public class PdfController {
    private final PdfGeneratorService pdfGeneratorService;
    private final RecipeService recipeService;

    public PdfController(PdfGeneratorService pdfGeneratorService, RecipeService recipeService) {
        this.pdfGeneratorService = pdfGeneratorService;
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> generateRecipePdf(@PathVariable Long id) {
        try {
            Recipe recipe = recipeService.getRecipeById(id);
            byte[] pdfBytes = pdfGeneratorService.generatePdf(recipe);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "recipe_" + id + ".pdf");
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);


        }
    }
}