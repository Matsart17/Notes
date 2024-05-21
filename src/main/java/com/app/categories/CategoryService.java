package com.app.categories;

import com.app.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


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

    public Category getCategory(int categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        return category.orElseThrow(() -> new NotFoundException("Category with id " + categoryId + " not exists"));
    }


    public Category getCategory(String name) {
        Category category = categoryRepository.findByName(name);
        return category;
    }

    public void merge(Category category, Category tempCategory) {
        String name = tempCategory.getName();
        String description = tempCategory.getDescription();
        if (description != null) {
            category.setName(name);
        }
        if (description != null) {
            category.setDescription(description);
        }
    }

    public Category putCategory(Category category) {
        if (category != null) {
            if (category.getName() == "") {
                category = null;
            } else if (categoryRepository.findByName(category.getName()) != null) {
                category = categoryRepository.findByName(category.getName());
            } else if (category.getName() != null) {
                saveCategory(category);
            }
        }
        return category;
    }

    public ResponseEntity<?> saveCategory(Category category) {
        if (categoryRepository.findByName(category.getName()) != null) {
            category = categoryRepository.findByName(category.getName());
        }
        categoryRepository.save(category);
        return ResponseEntity.status(201).body(category);
    }

    public void deleteCategory(int categoryId) {
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Note not found with id " + categoryId));
        categoryRepository.delete(category);
    }


}
