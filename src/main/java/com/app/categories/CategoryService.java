package com.app.categories;

import com.app.exceptions.NotFoundException;
import com.app.notes.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategory(String name) {
        Category category = categoryRepository.findByName(name);
        return category;
    }

    public void merge(Category category, Category tempCategory) {
        String name = tempCategory.getName();
        String description = tempCategory.getDescription();
        if (name != null || description != null) {
            category.setName(name);
            category.setDescription(description);
        }
    }

    public void findCategory(Set<Category> categories) {
        categories.forEach(this::checkCategory);
    }

    private void checkCategory(Category category) {
        System.out.println(category);
        if (categoryRepository.findByName(category.getName()) == null)
            categoryRepository.save(category);
    }

    public ResponseEntity<?> saveCategory(Category category) {
        categoryRepository.save(category);
        return ResponseEntity.status(201).body(category);
    }
}
