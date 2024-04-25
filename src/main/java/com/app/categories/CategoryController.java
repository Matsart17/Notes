package com.app.categories;

import com.app.notes.Note;
import com.app.notes.NotesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/categories")
@RestController
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }
    @GetMapping(path = "{categoryName}")
    public List<Category> getCategory(@PathVariable String categoryName) {
        return List.of(categoryService.getCategory(categoryName));
    }
    @PostMapping
    public ResponseEntity<?> createNewCategory(@Valid @RequestBody Category category) {
        return categoryService.saveCategory(category);
    }
    @PutMapping(path = "{categoryName}")
    public ResponseEntity<?> checkCategory(@RequestBody Category tempCategory, @PathVariable String categoryName) {
        Category existingCategory = categoryService.getCategory(categoryName);
        categoryService.merge(existingCategory, tempCategory);
        return categoryService.saveCategory(existingCategory);
    }



}
