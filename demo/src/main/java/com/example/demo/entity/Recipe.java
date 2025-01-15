package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Size(min = 1)
    @ElementCollection
    private List<String> ingredients;

    @Column(length = 1000)
    private String instructions;

    @ManyToOne
    private Category category;
}

