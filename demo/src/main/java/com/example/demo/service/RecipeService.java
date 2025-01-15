package com.example.demo.service;

import com.example.demo.entity.Recipe;
import com.example.demo.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> searchRecipesByIngredient(String ingredient) {
        return recipeRepository.findByIngredientsContaining(ingredient);
    }
}

