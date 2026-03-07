package com.dauphine.blogger.controllers;

import com.dauphine.blogger.model.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/categories")
@Tag(name = "Category API")
public class CategoryController {

    private final List<Category> temporaryCategories;

    public CategoryController() {
        this.temporaryCategories = new ArrayList<>();
        this.temporaryCategories.add(new Category(UUID.randomUUID(), "my first category"));
        this.temporaryCategories.add(new Category(UUID.randomUUID(), "my second category"));
        this.temporaryCategories.add(new Category(UUID.randomUUID(), "my third category"));
    }

    @GetMapping
    @Operation(summary = "Retrieve all categories")
    public List<Category> retrieveAllCategories() {
        return temporaryCategories;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a category by id")
    public Category retrieveCategoryById(@PathVariable UUID id) {
        return temporaryCategories.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    @Operation(summary = "Create a new category")
    public Category createCategory(@RequestBody String name) {
        Category category = new Category(UUID.randomUUID(), name);
        temporaryCategories.add(category);
        return category;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the name of a category")
    public Category updateCategory(@PathVariable UUID id, @RequestBody String name) {
        for (Category category : temporaryCategories) {
            if (category.getId().equals(id)) {
                category.setName(name);
                return category;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing category")
    public void deleteCategory(@PathVariable UUID id) {
        temporaryCategories.removeIf(c -> c.getId().equals(id));
    }
}