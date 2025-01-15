package com.example.demo.repository;

import com.example.demo.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByIngredientsContaining(String ingredient);

    @Query("SELECT DISTINCT r FROM Recipe r " +
            "JOIN r.ingredients i " +
            "WHERE i.name IN :ingredientNames " +
            "GROUP BY r.id " +
            "HAVING COUNT(i.id) = :ingredientCount")
    List<Recipe> findRecipesByIngredients(@Param("ingredientNames") List<String> ingredientNames,
                                          @Param("ingredientCount") Long ingredientCount);
}

