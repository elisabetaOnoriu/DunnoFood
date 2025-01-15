package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    public CategoryServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCategories() {
        List<Category> mockCategories = Arrays.asList(
                new Category() {{ setId(1L); setName("Vegetarian"); }},
                new Category() {{ setId(2L); setName("Vegan"); }}
        );

        when(categoryRepository.findAll()).thenReturn(mockCategories);

        List<Category> categories = categoryService.getAllCategories();
        assertEquals(2, categories.size());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void testCreateCategory() {
        Category category = new Category();
        category.setName("Gluten-Free");

        when(categoryRepository.save(category)).thenReturn(category);

        Category savedCategory = categoryService.createCategory(category);
        assertNotNull(savedCategory);
        assertEquals("Gluten-Free", savedCategory.getName());
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    void testGetCategoryById() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Dessert");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Category foundCategory = categoryService.getCategoryById(1L);
        assertNotNull(foundCategory);
        assertEquals("Dessert", foundCategory.getName());
        verify(categoryRepository, times(1)).findById(1L);
    }
}

