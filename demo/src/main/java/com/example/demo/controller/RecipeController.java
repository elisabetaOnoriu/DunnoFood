package com.example.demo.controller;

import com.example.demo.entity.Recipe;
import com.example.demo.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/search")
    public List<Recipe> searchRecipes(@RequestParam String ingredient) {
        return recipeService.searchRecipesByIngredient(ingredient);
    }
}

