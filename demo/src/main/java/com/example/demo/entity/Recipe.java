package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "instructions", columnDefinition = "TEXT")
    private String instructions;

    @ManyToMany
    @JoinTable(
            name = "RecipeIngredient", // Numele tabelului de legătură
            joinColumns = @JoinColumn(name = "recipe_id"), // Coloana din tabela Recipe
            inverseJoinColumns = @JoinColumn(name = "ingredient_id") // Coloana din tabela Ingredient
    )
    private Set<Ingredient> ingredients;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
