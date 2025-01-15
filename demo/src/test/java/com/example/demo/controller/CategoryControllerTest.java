package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    public CategoryControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCategories() {
        List<Category> mockCategories = Arrays.asList(
                new Category() {{ setId(1L); setName("Vegetarian"); }},
                new Category() {{ setId(2L); setName("Vegan"); }}
        );

        when(categoryService.getAllCategories()).thenReturn(mockCategories);

        ResponseEntity<List<Category>> response = categoryController.getAllCategories();
        assertEquals(2, response.getBody().size());
        verify(categoryService, times(1)).getAllCategories();
    }

    @Test
    void testCreateCategory() {
        Category category = new Category();
        category.setName("Gluten-Free");

        when(categoryService.createCategory(category)).thenReturn(category);

        ResponseEntity<Category> response = categoryController.createCategory(category);
        assertNotNull(response.getBody());
        assertEquals("Gluten-Free", response.getBody().getName());
        verify(categoryService, times(1)).createCategory(category);
    }
}

